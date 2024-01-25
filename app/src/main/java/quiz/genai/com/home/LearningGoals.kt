package quiz.genai.com.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.appUsage.TimeTracker
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.utils.ArcComposable
import quiz.genai.com.utils.Progress
import quiz.genai.com.utils.calculateProgress
import quiz.genai.com.utils.millisToMinutesSeconds
import java.time.LocalDate

@Composable
fun LearningGoalsSection(timeTracker: TimeTracker, totalTime: Long) {
    Column(verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(30.dp))
        var animStart by remember {
            mutableStateOf(true)
        }
        val progress = remember {
            calculateProgress(totalTime,  30 * 60 * 1000)
        }

        LaunchedEffect(key1 = Unit) {
            animStart = true
        }
        println("animatedProgress: $progress")
        println("animatedProgress2: ${millisToMinutesSeconds(timeTracker.getTotalTimeSpent())}")

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Learning Goals",
                    fontSize = 27.sp,
                    fontFamily = monteEB,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    color = textColor
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "Learn every day, see your stats soar and stand out " +
                            "in the competitive world",
                    fontSize = 10.sp,
                    fontFamily = monteEB,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    softWrap = true,
                    modifier = Modifier.fillMaxWidth(0.75f),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                )
            }
        }

        Spacer(modifier = Modifier.size(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ArcComposable(
                modifier = Modifier.padding(10.dp),
                text = millisToMinutesSeconds(timeTracker.getTotalTimeSpent()),
                progress = progress,
            )
        }

        Progress(modifier = Modifier.offset(y = (-120).dp))

        Spacer(modifier = Modifier.size(20.dp))

        Row(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-120).dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Start a new Streak",
                    fontSize = 21.sp,
                    fontFamily = monteEB,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    color = textColor
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = "Learn daily & reach your goals",
                    fontSize = 9.sp,
                    fontFamily = monteEB,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
                    softWrap = true,
                    modifier = Modifier.fillMaxWidth(0.75f),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                )
            }
        }


    }
}