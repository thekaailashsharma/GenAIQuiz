package quiz.genai.com

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.ui.theme.backGround

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DemoQuiz(viewModel: QuizViewModel, paddingValues: PaddingValues) {
    val quizState = viewModel.state.collectAsState()
    val quizTopic = viewModel.quizTopic.collectAsState()
    val quizLevel = viewModel.quizLevel.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround)
            .padding(bottom = paddingValues.calculateBottomPadding() + 10.dp)
    ) {
        when (quizState.value) {
            is QuizState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is QuizState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize().background(backGround),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Error: ${(quizState.value as QuizState.Error).message}")

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {
                        val prompt =
                            "You're a professor. I want to make a quiz. " +
                                    "The topic of the quiz is ${quizTopic.value}. The format of the quiz is as follows -" +
                                    " 5 questions in total. out of which 5 will be single correct option MCQ with 4 options." +
                                    " The level of the quiz will be also given in the prompt as variable as 0-5 (5 being the hardest.). " +
                                    "Level of quiz is ${quizLevel.value}. Now you're task is to design  a quiz. " +
                                    "There's a catch Output has to be of certain format only. " +
                                    "The format of the output is very strict and is as follows - " +
                                    "1. Question: {Question} 2. Option A: {Option A}" +
                                    "3. Option B: {Option B}4. Option C: {Option C}5. Option D: {Option D}" +
                                    "6. Correct Option: {Correction Option as 1/2/3/4 in the format " +
                                    "Correct Option: [Correct option Number onlyyyyyyyy.]. }" +
                                    "7. Explanation of the correct option." +
                                    "Such for all 5 of the questions " +
                                    "GIVE OUTPUT IN THE FORMAT I ASKED ONLY. DO NOT CHANGE THE output FORMAT. " +
                                    "DO NOT. DO NOT change the FORMAT. I CANNOT TOLERATE CHANGE IN FORMAT " +
                                    "I want output in same pattern including spaces, colons etc. Exact same."
                        viewModel.getQuiz(
                            prompt = prompt,
                        )
                    }) {
                        Text(text = "Start Quiz", color = Color.White)
                    }
                }
            }

            is QuizState.Success -> {

                QuizScreen(viewModel = viewModel, quiz = (quizState.value as QuizState.Success).quiz)

//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(15.dp),
//                ) {
//                    val quiz = (quizState.value as QuizState.Success).quiz
//                    items(quiz.questions) { index ->
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Text(
//                            text = index.questionText,
//                            color = Color.White,
//                            fontSize = 20.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                        if (index.options.size in 1..3) {
//                            Text(text = "Error: Question does not have 4 options")
//                            return@items
//                        }
//
//                        val correctOption = index.correctOption
//
//                        LazyColumn(
//                            modifier = Modifier
//                                .padding(10.dp)
//                                .height(130.dp)
//                        ) {
//                            itemsIndexed(index.options) { index, option ->
//                                println("Optionnnn: ${index} && Correct Option: $correctOption")
//                                val textColor = if (index + 1 == correctOption) {
//                                    Color(0xFF7EF582) // Set it to green if it's the correct option
//                                } else {
//                                    Color.White // Otherwise, keep it white
//                                }
//
//                                Spacer(modifier = Modifier.height(7.dp))
//                                Text(
//                                    text = option.option,
//                                    color = textColor,
//                                    fontSize = 15.sp,
//                                    fontWeight = FontWeight.Bold,
//                                )
//                            }
//                        }
//
////                        val textColor = Color.White
////                        Spacer(modifier = Modifier.height(10.dp))
////                        Text(
////                            text = index.options[0].option,
////                            color = textColor,
////                            fontSize = 15.sp,
////                            fontWeight = FontWeight.Bold,
////                        )
////                        Spacer(modifier = Modifier.height(7.dp))
////                        Text(
////                            text = index.options[1].option,
////                            color = textColor,
////                            fontSize = 15.sp,
////                        )
////                        Spacer(modifier = Modifier.height(7.dp))
////                        Text(
////                            text = index.options[2].option,
////                            color = textColor,
////                            fontSize = 15.sp,
////                        )
////                        Spacer(modifier = Modifier.height(7.dp))
////                        Text(
////                            text = index.options[3].option,
////                            color = textColor,
////                            fontSize = 15.sp,
////                        )
//                        Spacer(modifier = Modifier.height(10.dp))
//                        Text(
//                            text = index.explanation,
//                            color = Color(0xFF00BCD4),
//                            fontSize = 15.sp,
//                        )
//                        Spacer(modifier = Modifier.height(20.dp))
//                        Divider(color = Color.White)
//                        Spacer(modifier = Modifier.height(10.dp))
//                    }
//
//                }
            }

            QuizState.Idle -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        value = quizTopic.value,
                        onValueChange = {
                            viewModel.setQuizTopic(it)
                        },
                        label = {
                            Text(text = "Enter Topic of the Quiz", color = Color.White)
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFFF1D686),
                            unfocusedBorderColor = Color.White,
                            cursorColor = Color(0xFFFDBE03),
                        ),
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Slider(
                        value = quizLevel.value,
                        onValueChange = {
                            viewModel.setQuizLevel(it)
                        },
                        valueRange = 0f..5f,
                        steps = 1,
                        modifier = Modifier.padding(10.dp),
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFFDBE03),
                            activeTrackColor = Color(0xFFFDBE03),
                            inactiveTrackColor = Color(0xFF7EF582),
                        ),
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            val prompt =
                                "You're a professor. I want to make a quiz. " +
                                        "The topic of the quiz is ${quizTopic.value}. The format of the quiz is as follows -" +
                                        " 5 questions in total. out of which 5 will be single correct option MCQ with 4 options." +
                                        " The level of the quiz will be also given in the prompt as variable as 0-5 (5 being the hardest.). " +
                                        "Level of quiz is ${quizLevel.value}. Now you're task is to design  a quiz. " +
                                        "There's a catch Output has to be of certain format only. " +
                                        "The format of the output is very strict and is as follows - " +
                                        "1. Question: {Question} 2. Option A: {Option A}" +
                                        "3. Option B: {Option B}4. Option C: {Option C}5. Option D: {Option D}" +
                                        "6. Correct Option: {Correction Option as 1/2/3/4 in the format " +
                                        "Correct Option: [Correct option Number onlyyyyyyyy.]. }" +
                                        "7. Explanation of the correct option." +
                                        "Such for all 5 of the questions " +
                                        "GIVE OUTPUT IN THE FORMAT I ASKED ONLY. DO NOT CHANGE THE output FORMAT. " +
                                        "DO NOT. DO NOT change the FORMAT. I CANNOT TOLERATE CHANGE IN FORMAT " +
                                        "I want output in same pattern including spaces, colons etc. Exact same."

                            viewModel.getQuiz(
                                prompt = prompt,
                            )
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFAE6A9),
                            contentColor = Color.Black,

                            )
                    ) {
                        Text(text = "Start Quiz", color = Color.Black)
                    }
                }
            }
        }
    }

}

