package quiz.genai.com.profile

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import quiz.genai.com.home.CoursesChips
import quiz.genai.com.home.LearningGoalsSection
import quiz.genai.com.home.TopicsChip
import quiz.genai.com.home.dummyTopics
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow
import quiz.genai.com.utils.ProfileImage

@Composable
fun ProfileScreen(paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backGround)
            .padding(bottom = paddingValues.calculateBottomPadding()),
        contentPadding = PaddingValues(bottom = 0.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(80.dp))
            UserCard()
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(groupedBadges) { groupedBadges ->
            TimeLine(groupedBadges)
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            val lastTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trophy),
                    contentDescription = null,
                    modifier = Modifier
                        .size(130.dp),
                    alpha = 0.5f
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Showcase Daily",
                    color = lastTextColor.copy(0.75f),
                    fontSize = 24.sp,
                    fontFamily = monteEB,
                )
                Spacer(modifier = Modifier.height(0.dp))
                Text(
                    text = "Learning Achievements",
                    color = lastTextColor.copy(0.5f),
                    fontSize = 12.sp,
                    fontFamily = monteEB,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Your one-stop sharable profile is here.",
                    color = lastTextColor.copy(0.75f),
                    fontSize = 10.sp,
                    fontFamily = monteEB,
                )
            }

            Spacer(modifier = Modifier.size(80.dp))
        }
    }
}

