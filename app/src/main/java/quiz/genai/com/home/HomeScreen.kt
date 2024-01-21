package quiz.genai.com.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.R
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.indigo
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow
import quiz.genai.com.utils.ProfileImage

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 20.dp)
            .background(backGround)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
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
                        .size(70.dp)
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
                    text = "Hello, Vaishnava",
                    color = indigo,
                    fontFamily = monteEB,
                    fontSize = 17.sp
                )
            }

            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        TopicsChip(topics = dummyTopics)

        Spacer(modifier = Modifier.height(10.dp))

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

    }
}