package quiz.genai.com.utils

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.home.DailyData
import quiz.genai.com.home.dummyWeeklyGoals
import quiz.genai.com.home.isDayOfWeekAfterToday
import quiz.genai.com.home.isGoalAchieved
import quiz.genai.com.home.isToday
import quiz.genai.com.toPx
import quiz.genai.com.ui.theme.monte
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor
import java.time.LocalDate
import java.util.Date
import java.util.Locale

@Composable
fun ArcComposable(
    modifier: Modifier,
    text: String,
    progress: Float = 0.5f, // Progress value between 0.0 and 1.0
    completedColor: Color = Color(0xFF119E78),
    remainingColor: Color = Color(0xFFe4e4e4),
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val sweepAngle = 180f * progress
    Box(
        modifier = modifier.background(Color.Transparent)
    ) {
        Canvas(
            modifier = Modifier
                .size((screenWidth / 1.4).dp)
                .height(200.dp)
        ) {
            // Draw the remaining part of the arc
            drawArc(
                color = remainingColor,
                -180f + sweepAngle,
                180f - sweepAngle,
                useCenter = false,
                size = androidx.compose.ui.geometry.Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )

            // Draw the completed part of the arc
            drawArc(
                color = completedColor,
                -180f,
                sweepAngle,
                useCenter = false,
                size = androidx.compose.ui.geometry.Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-30).dp)
        ) {
            Text(
                text = "Today's Progress",
                color = textColor.copy(0.75f),
                fontSize = 15.sp,
                fontFamily = monte,
                modifier = Modifier.offset(y = (-20).dp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = text,
                color = textColor,
                fontSize = 33.sp,
                fontFamily = monteEB,
                softWrap = true,
                modifier = Modifier.offset(y = (-20).dp)
            )
            Spacer(modifier = Modifier.size(5.dp))
            Text(
                text = "of your 30-minute goal",
                color = textColor.copy(0.75f),
                fontSize = 15.sp,
                fontFamily = monte,
                softWrap = true,
                modifier = Modifier.offset(y = (-20).dp),
                fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold,
            )

        }
    }
}


fun calculateProgress(currentProgress: Long, setGoal: Long): Float {
    if (setGoal <= 0) {
        throw IllegalArgumentException("Goal must be greater than 0.")
    }

    val progressPercentage = if (currentProgress >= 0) {
        currentProgress.toFloat() / setGoal.toFloat()
    } else {
        0f
    }

    println("progressPercentage: $progressPercentage")

    return when {
        progressPercentage < 0 -> 0f
        progressPercentage > 1 -> 1f
        else -> progressPercentage
    }
}

fun millisToMinutesSeconds(millis: Long): String {
    if (millis < 0) {
        throw IllegalArgumentException("Input time must be non-negative.")
    }
    println("millis: $millis")
    val hours = millis / (1000 * 60 * 60)
    val minutes = (millis % (1000 * 60 * 60)) / (1000 * 60)

    return String.format("%02d:%02d", hours, minutes)
}


@Composable
fun Progress(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(dummyWeeklyGoals.size) { index ->
            val isGoalCompleted = remember {
                mutableStateOf(
                    isGoalAchieved(
                        dummyWeeklyGoals[index].days.recordedProgress,
                        dummyWeeklyGoals[index].days.setProgress
                    )
                )
            }
            val isDayAfterToday = remember {
                mutableStateOf(
                    isDayOfWeekAfterToday(
                        dummyWeeklyGoals[index].days.name.dayOfWeek
                    )
                )
            }
            val isDayToday = remember {
                mutableStateOf(
                    dummyWeeklyGoals[index].days.name.dayOfWeek == LocalDate.now().dayOfWeek
                )
            }
            println("isDayAfterToday: ${isDayAfterToday.value} && day: ${dummyWeeklyGoals[index].days.name.day}")
            if (!isDayAfterToday.value) {
                Circle(
                    modifier = Modifier.size(30.dp),
                    isGoalAchieved = isGoalCompleted.value,
                    isToday = isDayToday.value
                ) {
                    Text(
                        text = dummyWeeklyGoals[index].days.name.day,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                }
            } else {
                Circle(
                    modifier = Modifier.size(30.dp),
                    isGoalAchieved = false,
                    isToday = false
                ) {
                    Text(
                        text = dummyWeeklyGoals[index].days.name.day,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray
                    )

                }
            }
            if (index < dummyWeeklyGoals.size - 1)
                Line(
                    modifier = Modifier.weight(1f),
                    isGoalAchieved = if (!isDayAfterToday.value) isGoalCompleted.value else false
                )
        }
    }
}

@Composable
fun Circle(
    isToday: Boolean = false,
    isGoalAchieved: Boolean,
    modifier: Modifier,
    content: @Composable (BoxScope.() -> Unit) = { },
) {
    Box(
        modifier = modifier.drawBehind {
            drawCircle(
                color = if (isToday)
                    if (isGoalAchieved)
                        Color(0xFF119E78) else Color.White else
                    if (isGoalAchieved)
                        Color(0xFF119E78) else Color.DarkGray,
                style = Stroke(2.dp.toPx())
            )
        },
        contentAlignment = Alignment.Center,
        content = content
    )
}

@Composable
fun JobsCircle(
    isToday: Boolean = false,
    isGoalAchieved: Boolean,
    modifier: Modifier,
    content: @Composable (BoxScope.() -> Unit) = { },
) {
    Box(
        modifier = modifier
            .then(
                if (isGoalAchieved) Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF2C00C7)) else Modifier
            )
            .drawBehind {
                drawRoundRect(
                    color =
                    Color(0xFFD5D5D5),
                    style = Stroke(2.dp.toPx()),
                    cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx())
                )
            },
        contentAlignment = Alignment.Center,
        content = content
    )
}


@Composable
fun Line(modifier: Modifier, isGoalAchieved: Boolean = false) {
    Canvas(modifier = modifier) {
        drawLine(
            color = if (isGoalAchieved) Color(0xFF119E78) else Color.Transparent,
            start = Offset.Zero,
            end = Offset(size.width, 0f),
            strokeWidth = 1.dp.toPx()
        )
    }
}

@Composable
fun JobsLine(modifier: Modifier, isGoalAchieved: Boolean = false) {
    Canvas(modifier = modifier.offset(x = (0).dp)) {
        drawLine(
            color = Color(0xFFD5D5D5),
            start = Offset(0f, 0f),
            end = Offset(58f, 0f),
            strokeWidth = 3.dp.toPx()
        )
    }
}


