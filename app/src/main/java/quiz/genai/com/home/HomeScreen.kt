package quiz.genai.com.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import quiz.genai.com.R
import quiz.genai.com.appUsage.TimeTracker
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow
import quiz.genai.com.utils.ProfileImage

@Composable
fun HomeScreen(
    time: Long,
    timeTracker: TimeTracker,
    paddingValues: PaddingValues
) {
    val state = rememberCollapsingToolbarScaffoldState()
    val progress = state.toolbarState.progress

    CollapsingToolbarScaffold(
        toolbar = {
            val textSize = (14 + (30 - 19) * state.toolbarState.progress)
            val imageSize = (40 + (30 - 9) * state.toolbarState.progress)
            Box(
                modifier = Modifier
                    .background(backGround)
                    .fillMaxWidth()
                    .height(90.dp)
                    .pin()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 10.dp)
                    .road(
                        whenCollapsed = Alignment.Center,
                        whenExpanded = Alignment.CenterStart
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileImage(
                        imageUrl = R.drawable.memoji,
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .size(imageSize.dp)
                            .border(
                                width = 1.dp,
                                color = yellow,
                                shape = CircleShape
                            )
                            .padding(2.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (state.toolbarState.progress > 0.8f) "Hello, Vaishnava" else "Hello,",
                        color = indigo,
                        fontFamily = monteEB,
                        fontSize = textSize.sp,
                    )
                }

                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(21.dp)
                )
            }

        },
        state = state,
        modifier = Modifier.fillMaxSize(),
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backGround)
                .padding(start = 10.dp, end = 10.dp, top = 20.dp)
                .padding(bottom = paddingValues.calculateBottomPadding())
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "What do you want to learn today?",
                color = yellow,
                fontFamily = monteEB,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))


            TopicsChip(topics = dummyTopics)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Hello, $time",
                color = indigo,
                fontFamily = monteEB,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "Courses",
                color = textColor,
                fontFamily = monteEB,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 3.dp)

            )

            Spacer(modifier = Modifier.height(5.dp))

            CoursesChips()

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Lectures",
                color = textColor,
                fontFamily = monteEB,
                fontSize = 17.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(5.dp))

            CoursesChips()

            LearningGoalsSection(timeTracker = timeTracker)

            val lastTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.skills),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp)
                        .alpha(0.45f),
                    colorFilter = ColorFilter.lighting(
                        Color(0xFF927DFF),
                        Color(0xFFE6E5F0)
                    ),
                    alpha = 0.25f,
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Elevate yourself",
                    color = lastTextColor.copy(0.75f),
                    fontSize = 24.sp,
                    fontFamily = monteEB,
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "with Continuous Learning",
                    color = lastTextColor.copy(0.5f),
                    fontSize = 12.sp,
                    fontFamily = monteEB,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Crafted with ❤️ by The Centennials",
                    color = lastTextColor.copy(0.75f),
                    fontSize = 10.sp,
                    fontFamily = monteEB,
                )
            }

            Spacer(modifier = Modifier.size(80.dp))


        }
    }
}