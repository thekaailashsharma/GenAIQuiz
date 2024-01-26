package quiz.genai.com

import android.content.res.Resources
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import quiz.genai.com.navController.Screens
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.lightGray
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.orange
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow

@Composable
fun CourseDetails(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: QuizViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround)
            .padding(bottom = paddingValues.calculateBottomPadding() + 8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "UI/UX Design",
                    color = indigo,
                    fontFamily = monteEB,
                    fontSize = 30.sp,
                )

                Icon(
                    imageVector = Icons.Outlined.ModeComment,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            GradualSpacer(thickness = 3.dp)
            Spacer(modifier = Modifier.height(17.dp))
            LevelDialogBox(progress = 0.4f)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StackedCards()
                Text(
                    text = "+40 hours",
                    color = Color.White.copy(0.8f),
                    fontFamily = monteEB,
                    fontSize = 16.sp,
                    softWrap = true,
                    modifier = Modifier
                        .clip(CircleShape)

                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            GradualSpacer(thickness = 3.dp)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "UI/UX Design",
                    color = textColor,
                    fontFamily = monteEB,
                    fontSize = 15.sp,
                )

                Text(
                    text = "See all",
                    color = textColor,
                    fontFamily = monteEB,
                    fontSize = 13.sp,
                )
            }
        }

        itemsIndexed(uiUxModules) { index, it ->
            ModuleCard(module = it, navController = navController)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            QuizCard(
                topic = "UI/UX",
                level = 3f,
                navController = navController,
                viewModel = viewModel
            )
        }

    }
}

@Composable
fun ModuleCard(module: Module, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, indigo.copy(0.35f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.BottomEnd)
                    .offset(x = 2.dp, y = 2.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF105E14)),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, lightGray.copy(0.95f))
            ) {
                Button(
                    onClick = {
                        navController.navigate(Screens.ArticleScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Start Now",
                            color = textColor,
                            fontFamily = monteEB,
                            fontSize = 15.sp,
                            softWrap = true,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            imageVector = Icons.Outlined.ArrowUpward,
                            contentDescription = null,
                            tint = Color.Green.copy(0.5f),
                            modifier = Modifier
                                .size(20.dp)
                                .rotate(45f)
                        )


                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(horizontal = 7.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = module.name,
                            color = textColor,
                            fontFamily = monteEB,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth(0.6f),
                            softWrap = true,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = module.name,
                            color = yellow.copy(0.95f),
                            fontFamily = monteEB,
                            fontSize = 13.sp,
                            modifier = Modifier.fillMaxWidth(0.6f),
                            softWrap = true,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }

                Row(modifier = Modifier.padding(horizontal = 7.dp)) {

                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = null,
                                tint = yellow,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = module.rating.toString(),
                                color = textColor,
                                fontFamily = monteEB,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(end = 3.dp)
                            )
                        }
                    }
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Timer,
                                contentDescription = null,
                                tint = indigo,
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${module.hoursRequired} hours",
                                color = textColor,
                                fontFamily = monteEB,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(end = 3.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }

}

@Composable
fun QuizCard(
    topic: String,
    level: Float,
    navController: NavController,
    viewModel: QuizViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, indigo.copy(0.35f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.BottomEnd)
                    .offset(x = 2.dp, y = 2.dp),
                elevation = CardDefaults.cardElevation(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF105E14)),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, lightGray.copy(0.95f))
            ) {
                Button(
                    onClick = {
                        viewModel.setQuizTopic(topic)
                        viewModel.setQuizLevel(level)
                        navController.navigate(Screens.QuizScreen.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Start Now",
                            color = textColor,
                            fontFamily = monteEB,
                            fontSize = 15.sp,
                            softWrap = true,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Icon(
                            imageVector = Icons.Outlined.ArrowUpward,
                            contentDescription = null,
                            tint = Color.Green.copy(0.5f),
                            modifier = Modifier
                                .size(20.dp)
                                .rotate(45f)
                        )


                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(horizontal = 7.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.Start) {
                        Text(
                            text = "Take a Quiz",
                            color = textColor,
                            fontFamily = monteEB,
                            fontSize = 20.sp,
                            modifier = Modifier.fillMaxWidth(0.6f),
                            softWrap = true,
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Get Verified Badge once you get above 70% marks",
                            color = yellow.copy(0.95f),
                            fontFamily = monteEB,
                            fontSize = 13.sp,
                            modifier = Modifier.fillMaxWidth(0.6f),
                            softWrap = true,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }

}


@Composable
fun GradualSpacer(thickness: Dp, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thickness)
            .padding(horizontal = 4.dp)
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                    shape = CircleShape
                )
                .scale(scaleX = 0.8f, scaleY = 1f)
                .clip(CircleShape)
                .graphicsLayer(translationX = (-2).dp.toPx())
        )
    }
}

fun Dp.toPx(): Float {
    return (this.value * Resources.getSystem().displayMetrics.density)
}
