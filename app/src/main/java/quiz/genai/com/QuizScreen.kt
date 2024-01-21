package quiz.genai.com

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Circle
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.dto.quiz.Quiz
import quiz.genai.com.dto.quiz.QuizOption
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(viewModel: QuizViewModel, quiz: Quiz) {

    val quizTopic = viewModel.quizTopic.collectAsState()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        quiz.questions.size
    }

    HorizontalPager(state = pagerState) { page ->
        val currentQuestion = quiz.questions[page]

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(start = 10.dp, top = 40.dp),
               horizontalArrangement = Arrangement.Start
           ) {
               Text(
                   text = "${quizTopic.value
                       .replaceFirstChar
                       { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }} quiz",
                   color = Color.LightGray,
                   fontSize = 15.sp,
                   fontWeight = FontWeight.Bold,
                   textAlign = TextAlign.Center,
                   modifier = Modifier.fillMaxWidth()
               )
           }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                val questionText = "Question"

                val annotatedString = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)) {
                        append(questionText)
                    }
                    append(" ")
                    withStyle(style = SpanStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)) {
                        append("${page + 1}/${quiz.questions.size}")
                    }
                }

                Text(
                    text = annotatedString,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )

            }

            DashedProgressBar(totalDashes = quiz.questions.size, currentPage = page)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = currentQuestion.questionText,
                color = Color(0xFFFFFFFF),
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (currentQuestion.options.size in 1..3) {
                Text(text = "Error: Question does not have 4 options", color = Color.Red)
            } else {
                OptionsList(currentQuestion.options, currentQuestion.correctOption)
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = currentQuestion.explanation,
                color = Color(0xFF00BCD4),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        // Handle skip button click
                        // You can implement skip logic or navigate to the next question
                        // based on your requirements.
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text(text = "Skip", color = Color.White)
                }

                Button(
                    onClick = {
                        // Handle next button click
                        // You can implement logic to navigate to the next question
                        // or submit the quiz based on your requirements.
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    Text(text = "Next", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun DashedProgressBar(
    totalDashes: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(8.dp)
    ) {
        val dashWidth = size.width / totalDashes
        val dashHeight = size.height
        val dashSpacing = 4.dp.toPx() // Adjust the spacing between dashes as needed

        for (i in 0 until totalDashes) {
            val dashColor = when {
                i < currentPage -> Color(0xFF7BE67F)
                i == currentPage -> Color(0xFFFC6178)
                else -> Color.White
            }

            drawLine(
                color = dashColor,
                start = Offset(i * (dashWidth + dashSpacing), dashHeight / 2),
                end = Offset((i + 1) * dashWidth + i * dashSpacing, dashHeight / 2),
                strokeWidth = 4.dp.toPx() // Adjust the stroke width as needed
            )
        }
    }
}


@Composable
fun OptionsList(options: List<QuizOption>, correctOption: Int) {
    LazyColumn {
        itemsIndexed(options) { index, option ->
            val borderColor = if (index + 1 == correctOption) {
                Color(0xFF7BE67F) // Green border for correct option
            } else {
                Color.Gray // Gray border for other options
            }

            Spacer(modifier = Modifier.height(7.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, borderColor)
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(50.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = option.option,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )

                    // Tick icon if this option is correct
                    if (index + 1 == correctOption) {
                        Icon(
                            Icons.Sharp.Done,
                            contentDescription = null,
                            tint = Color(0xFF7BE67F) ,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            Icons.Sharp.Circle,
                            contentDescription = null,
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(7.dp))
        }
    }
}
