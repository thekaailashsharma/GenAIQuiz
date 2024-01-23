package quiz.genai.com.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.R
import quiz.genai.com.ui.theme.backGround
import quiz.genai.com.ui.theme.buttonBackground
import quiz.genai.com.ui.theme.monteEB
import quiz.genai.com.ui.theme.orange
import quiz.genai.com.ui.theme.textColor
import quiz.genai.com.ui.theme.yellow
import quiz.genai.com.utils.CrossDashedDivider
import quiz.genai.com.utils.VerticalDashedDivider

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GroupedBadgesCard(
    groupedBadges: GroupedBadges,
    modifier: Modifier = Modifier,
    buttonBorder: Color = buttonBackground,
    isTranslated: Boolean = false,
    translation: Float = 0f,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 0.dp,
                top = 0.dp,
                bottom = 0.dp,
                end = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        groupedBadges.formattedDate?.let { date ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(0.1f)
                    .padding(start = 5.dp)
            ) {
                Text(
                    text = date.year,
                    color = buttonBackground,
                    fontFamily = monteEB,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = "${date.day}/${date.month} ",
                    color = textColor.copy(0.8f),
                    fontFamily = monteEB,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = date.dayOfWeek.name.subSequence(0, 3).toString(),
                    color = buttonBackground,
                    fontFamily = monteEB,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            }
        }
        CrossDashedDivider(
            color = Color.White,
            height = 300,
            dashWidth = 9f,
            gapWidth = 5f,
            modifier = Modifier.weight(0.1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent,
                    ), border = BorderStroke(1.dp, buttonBorder),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 7.dp)
                        .height(200.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backGround),
                    ) {
                        println("GroupedBadgesCard: ${groupedBadges}")
                        Spacer(modifier = Modifier.height(20.dp))
                        FlowRow(
                            maxItemsInEachRow = 3,
                            modifier = Modifier.padding(horizontal = 10.dp)
                        ) {
                            groupedBadges.badges.forEach { badge ->
                                Icon(
                                    painter = painterResource(badge.image),
                                    contentDescription = null,
                                    tint = Color.Unspecified,
                                    modifier = Modifier
                                        .size(77.dp)
                                        .padding(end = 10.dp)
                                )
                            }

                        }
                    }
                }
                if (isTranslated) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            Icons.Filled.EmojiEvents,
                            contentDescription = null,
                            tint = yellow,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 10.dp, top = 10.dp)
                                .size(44.dp)
                                .padding(end = 10.dp)
                        )
                    }
                }

            }
        }
    }
}





