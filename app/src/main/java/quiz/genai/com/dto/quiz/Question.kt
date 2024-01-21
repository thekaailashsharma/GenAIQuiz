package quiz.genai.com.dto.quiz

data class Question(
    val questionText: String,
    val options: List<QuizOption>,
    val correctOption: Int,
    val explanation: String
)

data class QuizOption(val option: String)