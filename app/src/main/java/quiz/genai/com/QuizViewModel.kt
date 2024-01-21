package quiz.genai.com

import android.app.Application
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import quiz.genai.com.dto.geminiBody.Content
import quiz.genai.com.dto.geminiBody.GeminiBody
import quiz.genai.com.dto.geminiBody.Part
import quiz.genai.com.dto.geminiOutput.GeminiOutput
import quiz.genai.com.dto.quiz.Question
import quiz.genai.com.dto.quiz.Quiz
import quiz.genai.com.dto.quiz.QuizOption
import quiz.genai.com.repository.ApiService
import java.util.Locale
import javax.inject.Inject
import java.util.regex.Pattern

@HiltViewModel
class QuizViewModel @Inject constructor(
    application: Application,
    private val repository: ApiService,
) : AndroidViewModel(application) {

    private val _quizLevel = MutableStateFlow(0f)
    val quizLevel: StateFlow<Float> = _quizLevel.asStateFlow()

    private val _quizTopic = MutableStateFlow<String>("")
    val quizTopic: StateFlow<String> = _quizTopic.asStateFlow()

    private val _state = MutableStateFlow<QuizState>(QuizState.Idle)
    val state: StateFlow<QuizState> = _state.asStateFlow()

    private val _output = MutableStateFlow<GeminiOutput?>(null)
    val output: StateFlow<GeminiOutput?> = _output.asStateFlow()


    private val _quiz = MutableStateFlow<Quiz?>(null)
    val quiz: StateFlow<Quiz?> = _quiz.asStateFlow()

    fun setQuizLevel(level: Float) {
        _quizLevel.value = level
    }

    fun setQuizTopic(topic: String) {
        _quizTopic.value = topic
    }

    fun getQuiz(prompt: String) {
        println("Promptttt: $prompt")
        viewModelScope.launch {
            _state.value = QuizState.Loading
            try {
                val body = GeminiBody(
                    contents = listOf(
                        Content(
                            listOf(
                                Part(
                                    text = prompt
                                )
                            )
                        )
                    ),
                )
                val response = repository.getQuiz(body)
                _output.value = response

                println("Responseeeee: ${response.candidates
                    ?.get(0)?.content?.parts?.get(0)?.text ?: ""}")

                _quiz.value = extractQuizData(
                    response.candidates
                        ?.get(0)?.content?.parts?.get(0)?.text ?: ""
                )
                _state.value = QuizState.Success(_quiz.value ?: Quiz(listOf()))

            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = QuizState.Error(e.message ?: "Unknown Error")
            }

        }
    }


    private fun extractQuizData(quizOutputString: String): Quiz {
        // Replace all newline characters with an empty string
        val cleanedString = quizOutputString.replace("\n", "")

        val questions = findAllQuestions(cleanedString)
        val optionsA = findAllOptions("Option A: (.+?)(?=\\d+\\. Option B:|$)", cleanedString)
        val optionsB = findAllOptions("Option B: (.+?)(?=\\d+\\. Option C:|$)", cleanedString)
        val optionsC = findAllOptions("Option C: (.+?)(?=\\d+\\. Option D:|$)", cleanedString)
        val optionsD = findAllOptions("Option D: (.+?)(?=\\d+\\. Correct Option:|$)", cleanedString)

        val correctOptions = findCorrectOptions("Correct Option: (\\d+)", cleanedString)

        val explanations = findAllExplanations("Explanation: (.+?)(?=\\d+\\. Question:|$)", cleanedString)

        val quizQuestions = mutableListOf<Question>()

        for (i in questions.indices) {
            val options = listOf(
                QuizOption(optionsA[i] ?: ""),
                QuizOption(optionsB[i] ?: ""),
                QuizOption(optionsC[i] ?: ""),
                QuizOption(optionsD[i] ?: "")
            )

            val question = correctOptions[i]?.let {
                Question(
                    questions[i],
                    options,
                    it,
                    explanations[i] ?: ""
                )
            }

            if (question != null) {
                quizQuestions.add(question)
            }
        }

        return Quiz(quizQuestions)
    }

    private fun findAllQuestions(input: String): List<String> {
        val questionPattern = Pattern.compile("Question: (.+?)(?=\\d+\\. Option A:|$)")
        val questionMatcher = questionPattern.matcher(input)

        val questions = mutableListOf<String>()
        while (questionMatcher.find()) {
            questions.add(questionMatcher.group(1)?.trim() ?: "")
        }
        return questions
    }

    private fun findAllOptions(pattern: String, input: String): List<String?> {
        val optionPattern = Pattern.compile(pattern)
        val optionMatcher = optionPattern.matcher(input)

        val options = mutableListOf<String?>()
        while (optionMatcher.find()) {
            options.add(optionMatcher.group(1)?.trim())
        }
        return options
    }

    private fun findCorrectOptions(pattern: String, input: String): List<Int?> {
        val correctOptionPattern = Pattern.compile(pattern)
        val correctOptionMatcher = correctOptionPattern.matcher(input)

        val correctOptions = mutableListOf<Int?>()
        while (correctOptionMatcher.find()) {
            val correctOption = correctOptionMatcher.group(1)?.trim()?.takeIf { it.isNotEmpty() }?.get(0)?.toString()?.toInt()
            correctOptions.add(correctOption)
        }
        return correctOptions
    }


    private fun findAllExplanations(pattern: String, input: String): List<String?> {
        val explanationPattern = Pattern.compile(pattern)
        val explanationMatcher = explanationPattern.matcher(input)

        val explanations = mutableListOf<String?>()
        while (explanationMatcher.find()) {
            explanations.add(explanationMatcher.group(1)?.trim())
        }
        return explanations
    }



}


sealed class QuizState {
    object Idle : QuizState()
    object Loading : QuizState()
    data class Success(val quiz: Quiz) : QuizState()
    data class Error(val message: String) : QuizState()
}
