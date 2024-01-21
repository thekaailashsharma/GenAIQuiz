package quiz.genai.com.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.ui.theme.buttonBackground
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.textColor

data class Topic(
    val name: String,
)

val dummyTopics = listOf(
    Topic("UI/UX"),
    Topic("Android"),
    Topic("Kotlin"),
    Topic("Jetpack Compose"),
    Topic("Marketing"),
    Topic("Design"),
    Topic("Product"),
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopicsChip(topics: List<Topic>) {
    FlowRow(maxItemsInEachRow = 3) {
        topics.forEach { topic ->
            TopicChip(topic = topic)
        }
    }
}

@Composable
fun TopicChip(topic: Topic) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
        border = BorderStroke(1.dp, buttonBackground)
    ) {
        Text(
            text = topic.name,
            fontFamily = monteEB,
            fontSize = 14.sp,
            color = textColor,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}