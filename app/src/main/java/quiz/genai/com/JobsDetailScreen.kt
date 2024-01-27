package quiz.genai.com

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import quiz.genai.com.home.discover
import quiz.genai.com.home.jobsList
import quiz.genai.com.navController.Screens
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow
import quiz.genai.com.utils.JobsCircle
import quiz.genai.com.utils.JobsLine
import quiz.genai.com.utils.ProfileImage

@Composable
fun JobsDetailScreen(
    paddingValues: PaddingValues,
    navController: NavController,
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
                    text = "Hello Vaishnava",
                    color = indigo,
                    fontFamily = monteEB,
                    fontSize = 22.sp,
                )

                ProfileImage(
                    imageUrl = R.drawable.memoji,
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(24.dp)
                        .border(
                            width = 1.dp,
                            color = yellow,
                            shape = CircleShape
                        )
                        .padding(2.dp)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(7.dp))
            GradualSpacer(thickness = 3.dp)
            Spacer(modifier = Modifier.height(17.dp))
            Text(
                text = "Find Jobs",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontFamily = monteEB,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(17.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(discover.size) { index ->
                    val isGoalCompleted = remember {
                        mutableStateOf(
                            index == 0
                        )
                    }
                    JobsCircle(
                        modifier = Modifier
                            .width(80.dp)
                            .height(35.dp),
                        isGoalAchieved = isGoalCompleted.value,
                    ) {
                        Text(
                            text = discover[index],
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.White,
                            fontFamily = monteEB
                        )

                    }

                    JobsLine(
                        modifier = Modifier,
                        isGoalAchieved = true
                    )

                }
            }
            Spacer(modifier = Modifier.height(17.dp))
        }

        itemsIndexed(jobsList) { index, job ->
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(containerColor = job.color),
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(1.dp, indigo.copy(0.35f))
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .align(Alignment.TopEnd)
                                .offset(x = (4).dp, y = -2.dp),
                            elevation = CardDefaults.cardElevation(0.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF2B2B2B)),
                            shape = RoundedCornerShape(10.dp),
                            border = BorderStroke(3.dp, Color(0xFF4E4E4E))
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
                                        text = "Apply",
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
                                    Row(
                                        modifier = Modifier,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = job.company,
                                            color = job.textColor,
                                            fontFamily = monteEB,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.6f),
                                            softWrap = true,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(22.dp))

                                    Text(
                                        text = job.description,
                                        color = job.textColor.copy(0.75f),
                                        fontFamily = monteEB,
                                        fontSize = 9.sp,
                                        modifier = Modifier.fillMaxWidth(0.86f),
                                        softWrap = true,
                                        fontWeight = FontWeight.SemiBold,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        lineHeight = 15.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))
                                }

                            }

                            Row(modifier = Modifier.padding(horizontal = 7.dp)) {

                                Card(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier
                                        .padding(5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = job.color.copy(0.9f),
                                        contentColor = Color.White
                                    ),
                                    border = BorderStroke(1.dp, job.textColor)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(3.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Money,
                                            contentDescription = null,
                                            tint = job.textColor,
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = job.salary,
                                            color = job.textColor,
                                            fontFamily = monteEB,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(end = 3.dp)
                                        )
                                    }
                                }
                                Card(
                                    shape = RoundedCornerShape(5.dp),
                                    modifier = Modifier
                                        .padding(5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.Transparent,
                                        contentColor = Color.White
                                    ),
                                    border = BorderStroke(1.dp, job.textColor)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(3.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.LocationOn,
                                            contentDescription = null,
                                            tint = job.textColor,
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = job.location,
                                            color = job.textColor,
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
        }
    }
}