package quiz.genai.com

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.monteEB

val userImages = listOf(
    R.drawable.user,
    R.drawable.user,
    R.drawable.user,
    R.drawable.user,
    R.drawable.user,
    R.drawable.hours,
)


@Composable
fun StackedCards() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    Row(
        modifier = Modifier
            .padding(start = 10.dp)
            .height(80.dp)
    ) {
        Box(modifier = Modifier
            .height(80.dp)) {
            userImages.forEachIndexed { index, drawable ->
                val offsetX = (index * 25).dp
                val lastElementVisibility = if (index == userImages.lastIndex) 0f else 1f
                val modifier = Modifier
                    .size(50.dp)
                    .absoluteOffset(
                        x = offsetX,
                    )
                    .alpha(lastElementVisibility)
                    .align(Alignment.CenterStart)
                if (index != userImages.lastIndex) {
                    UserImage(modifier = modifier, image = drawable)
                } else {
                    UserImage(modifier = modifier, image = drawable, isTimerVisible = true)
                }
            }
        }
    }
}

@Composable
fun UserImage(modifier: Modifier, image: Int, isTimerVisible: Boolean = false) {
    Card(
        modifier = modifier,
        shape = CircleShape,
        border = BorderStroke(
            width = 3.dp,
            color = Color.White.copy(0.8f)
        ),
        elevation = CardDefaults.cardElevation(
            5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = indigo.copy(0.8f),
        )
    ) {
        if (!isTimerVisible) {
            Image(
                painter = painterResource(image),
                contentDescription = "user image",
            )
        } else {
            Text(
                text = "+40 hours",
                color = Color.Black,
                fontFamily = monteEB,
                fontSize = 10.sp,
                softWrap = true,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp,
            )
        }
    }
}
