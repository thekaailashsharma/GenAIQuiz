package quiz.genai.com.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val appGradient: Brush
    @Composable
    get() = Brush.horizontalGradient(
        0.0f to Color(0xFF927DFF).copy(alpha = 0.87f),
        500.0f to Color(0xFF1A1A1A),
    )
