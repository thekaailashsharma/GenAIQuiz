package quiz.genai.com.utils

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp



@Composable
fun VerticalDashedDivider(
    color: Color = Color.Black,
    height: Int = 100,
    dashWidth: Float = 4f,
    gapWidth: Float = 4f,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.height(height.dp)) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), 0f)
        drawLine(
            color = color,
            start = Offset(size.width / 2, 0f),
            end = Offset(size.width / 2, size.height),
            pathEffect = pathEffect
        )
    }
}


@Composable
fun CrossDashedDivider(
    color: Color = Color.Black,
    height: Int = 100,
    dashWidth: Float = 4f,
    gapWidth: Float = 4f,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.height(height.dp)) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth, gapWidth), 0f)

        // Draw vertical dashed line
        drawLine(
            color = color,
            start = Offset(size.width / 2, 0f),
            end = Offset(size.width / 2, size.height),
            pathEffect = pathEffect
        )

        // Draw horizontal dashed line
        drawLine(
            color = color,
            start = Offset(size.width / 2, size.height / 2),
            end = Offset(size.width, size.height / 2),
            pathEffect = pathEffect
        )

    }
}

