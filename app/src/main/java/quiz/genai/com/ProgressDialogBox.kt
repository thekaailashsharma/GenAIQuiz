package quiz.genai.com

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.lightGray
import quiz.genai.com.ui.theme.monte
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor

@Composable
fun LevelDialogBox(
    progress: Float,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = lightGray.copy(0.4f),
        ),
        border = BorderStroke(1.dp, indigo.copy(0.43f))
    ) {
        Column(
            modifier = Modifier
                .background(backGround),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(backGround)
                    .padding(horizontal = 15.dp, vertical = 19.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Progress",
                    textAlign = TextAlign.Center,
                    color = textColor,
                    fontFamily = monteEB,
                    fontSize = 25.sp,
                    softWrap = true
                )

                CircleComposable(
                    modifier = Modifier,
                    text = "85%",
                    progress = progress
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp)
                    .background(backGround),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "1/7 Complete",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontSize = 19.sp
                )

            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}


@Composable
fun CircleComposable(
    modifier: Modifier,
    text: String,
    progress: Float = 0.5f, // Progress value between 0.0 and 1.0
    completedColor: Color = Color(0xFF48c5a3),
    remainingColor: Color = Color(0xFFe4e4e4),
) {
    val sweepAngle = 360f * progress
    Box(
        modifier = modifier.background(Color.Transparent)
    ) {
        Canvas(modifier = Modifier.size(70.dp)) {
            // Draw the remaining part of the arc
            drawArc(
                color = remainingColor,
                -360f + sweepAngle,
                360f - sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )

            // Draw the completed part of the arc
            drawArc(
                color = completedColor,
                0f,
                sweepAngle,
                useCenter = false,
                size = Size(size.width, size.height),
                style = Stroke(8.dp.toPx(), cap = StrokeCap.Round)
            )
        }
        Text(
            modifier = Modifier.align(alignment = Alignment.Center),
            text = text,
            color = textColor,
            fontSize = 20.sp
        )
    }
}