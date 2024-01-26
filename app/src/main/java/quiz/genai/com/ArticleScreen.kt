package quiz.genai.com

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.lightGray
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.orange
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow

@Composable
fun ArticleScreen(paddingValues: PaddingValues) {
    val count = remember {
        mutableIntStateOf((10..50).random())
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backGround)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.uiux),
                    contentDescription = "some useful description",
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawWithCache {
                            val gradient =
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(
                                            0.8f
                                        )
                                    ),
                                    startY = size.height / 5.5f,
                                    endY = size.height
                                )
                            onDrawWithContent {
                                drawContent()
                                drawRect(
                                    gradient,
                                    blendMode = BlendMode.Multiply
                                )
                            }
                        },
                    contentScale = ContentScale.FillWidth
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = UiUxArticle,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 7.dp),
                    softWrap = true,
                    fontFamily = monteEB,
                    lineHeight = 25.sp
                )
                Spacer(modifier = Modifier.height(85.dp))
            }
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            count.intValue += 1
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = lightGray,
                        contentColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color.White.copy(0.5f))
                ) {
                    Row(
                        modifier = Modifier.padding(9.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painterResource(id = R.drawable.clap),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = count.value.toString(),
                            color = textColor,
                            fontFamily = monteEB,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(end = 3.dp)
                        )
                    }
                }
            }
        }
    }
}