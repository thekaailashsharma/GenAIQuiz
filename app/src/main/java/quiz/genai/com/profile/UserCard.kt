package quiz.genai.com.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        RotatedSkillsCard(
            modifier = Modifier
                .graphicsLayer(
                    rotationZ = 348f,
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0f, // Set pivot at the left-most border
                        pivotFractionY = 1f // Set pivot at the bottom-left corner
                    )
                ),
            color = Color(0xFFF5F378).copy(0.85f),
        )
        RotatedSkillsCard(
            modifier = Modifier
                .graphicsLayer(
                    rotationZ = 354f,
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0f, // Set pivot at the left-most border
                        pivotFractionY = 1f // Set pivot at the bottom-left corner
                    )
                ),
            color = Color(0xFFDCC1FF).copy(0.85f),

        )
        SkillsCard(
            modifier = Modifier
                .graphicsLayer(
                    rotationZ = 0f, // No rotation for the third card
                    transformOrigin = TransformOrigin(
                        pivotFractionX = 0f, // Set pivot at the left-most border
                        pivotFractionY = 1f // Set pivot at the bottom-left corner
                    )
                ),
        )
    }
}
