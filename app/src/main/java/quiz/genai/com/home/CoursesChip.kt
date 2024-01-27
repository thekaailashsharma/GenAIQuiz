package quiz.genai.com.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import quiz.genai.com.R
import quiz.genai.com.navController.Screens
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.lightGray
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.orange
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow
import quiz.genai.com.utils.ProfileImage
import kotlin.random.Random

data class Course(
    val title: String,
    val imageUrl: Int,
    val topics: List<Topic>,
    val rating: Float,
    val durationInHours: Int,
    val color: Color = yellow,
    val isPoweredBy: Boolean = Random.nextBoolean(),
    val companyName : String = arrayOf("Cognavi", "GenAI", "Google", "Facebook", "Amazon", "Microsoft").random()
)

val dummyCourses = listOf(
    Course(
        title = "UI/UX Design",
        imageUrl = R.drawable.sitting,
        topics = listOf(
            Topic("UI/UX"),
            Topic("Design"),
            Topic("Product"),
        ),
        rating = 4.5f,
        durationInHours = 10,
        color = yellow
    ),
    Course(
        title = "Android Development",
        imageUrl = R.drawable.robotics,
        topics = listOf(
            Topic("Android"),
            Topic("Kotlin"),
            Topic("Jetpack Compose"),
        ),
        rating = 4.5f,
        durationInHours = 10,
        color = indigo
    ),
    Course(
        title = "Marketing",
        imageUrl = R.drawable.marketing,
        topics = listOf(
            Topic("Marketing"),
            Topic("Design"),
            Topic("Product"),
        ),
        rating = 4.5f,
        durationInHours = 10,
        color = orange
    ),
    Course(
        title = "UI/UX Design",
        imageUrl = R.drawable.waiting,
        topics = listOf(
            Topic("UI/UX"),
            Topic("Design"),
            Topic("Product"),
        ),
        rating = 4.5f,
        durationInHours = 10,
        color = yellow
    ),
    Course(
        title = "Android Development",
        imageUrl = R.drawable.thinking,
        topics = listOf(
            Topic("Android"),
            Topic("Kotlin"),
            Topic("Jetpack Compose"),
        ),
        rating = 4.5f,
        durationInHours = 10,
        color = orange
    ),
    Course(
        title = "Marketing",
        imageUrl = R.drawable.handsshake,
        topics = listOf(
            Topic("Marketing"),
            Topic("Design"),
            Topic("Product"),
        ),
        rating = 4.5f,
        durationInHours = 10,
        color = indigo

    ),
)

@Composable
fun CoursesChips(navController: NavController) {
    LazyRow() {
        items(dummyCourses.sortedByDescending {
            it.isPoweredBy
        }) { index ->
            CourseChip(course = index, navController = navController)
        }
    }
}

@Composable
fun CourseChip(course: Course, navController: NavController) {
    CourseCard(course = course, navController)
}

@Composable
fun CourseCard(course: Course, navController: NavController) {
    var isBookMarked by remember {
        mutableStateOf(false)
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(5.dp)
            .height(220.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
            ) {
                navController.navigate(Screens.CourseDetails.route)
            },
        colors = CardDefaults.cardColors(
            containerColor = course.color,
            contentColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopEnd)
                    .offset(y = (-15).dp)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                    ) {
                        isBookMarked = !isBookMarked
                    },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (isBookMarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .width(33.dp)
                        .height(48.dp),
                    tint = if (isBookMarked) Color(0xFFed2c2c).copy(1f)
                    else Color.Black.copy(alpha = 0.6f)
                )
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = course.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(10.dp))
                        .offset(y = (-15).dp),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        Color.Black.copy(alpha = 0.9f)
                    )
                )
            }

//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.BottomEnd
//            ) {
            if (course.isPoweredBy) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.BottomEnd)
                        .padding(top = 10.dp, bottom = 10.dp)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        text = "Brought to",
                        color = lightGray.copy(0.45f),
                        fontFamily = monteEB,
                        fontSize = 6.sp,
                        modifier = Modifier.padding(end = 3.dp)
                    )
                    Text(
                        text = "you by",
                        color = lightGray.copy(0.45f),
                        fontFamily = monteEB,
                        fontSize = 6.sp,
                        modifier = Modifier.padding(end = 3.dp)
                    )
                    Text(
                        text = course.companyName,
                        color = lightGray.copy(0.75f),
                        fontFamily = monteEB,
                        fontSize = 9.sp,
                        modifier = Modifier.padding(end = 3.dp)
                    )
                }
            }

//            }



            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = course.title,
                        color = Color.Black.copy(0.7f),
                        fontFamily = monteEB,
                        fontSize = 15.sp,
                        modifier = Modifier.fillMaxWidth(0.3f),
                        softWrap = true,
                        fontWeight = FontWeight.SemiBold
                    )

                }

                Spacer(modifier = Modifier.height(7.dp))

                Row {

                    Card(
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black.copy(alpha = 0.3f),
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
                                text = course.rating.toString(),
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
                            containerColor = Color.Black.copy(alpha = 0.3f),
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
                                text = "${course.durationInHours} hours",
                                color = textColor,
                                fontFamily = monteEB,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(end = 3.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}




