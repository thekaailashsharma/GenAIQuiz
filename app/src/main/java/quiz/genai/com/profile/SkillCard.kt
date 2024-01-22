package quiz.genai.com.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import quiz.genai.com.R
import quiz.genai.com.ui.theme.appGradient
import quiz.genai.com.ui.theme.monteEB

@Composable
fun SkillsCard(modifier: Modifier = Modifier) {
    Card(colors = CardDefaults.cardColors(
        containerColor = Color.Transparent,
    ), border = BorderStroke(1.dp, Color(0xFF454164)),
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.skills),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.Center),
                colorFilter = ColorFilter.lighting(
                    Color(0xFF927DFF),
                    Color(0xFFE6E5F0)
                ))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(appGradient),

                ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Skills Card",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = monteEB,
                        fontWeight = FontWeight.Normal,

                        )
                    Icon(
                        painter = painterResource(R.drawable.nfc_icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(44.dp)
                            .padding(end = 10.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(R.drawable.memoji),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .offset(y = (-15).dp)
                            .padding(end = 10.dp),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                        alpha = 1f,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Vaishnava Pingale",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = monteEB,
                        fontWeight = FontWeight.ExtraBold,
                    )

                    Icon(
                        painter = painterResource(R.drawable.signature),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(end = 20.dp)
                            .size(44.dp)
                            .padding(end = 10.dp)
                    )
                }


            }
        }

    }
}

@Composable
fun RotatedSkillsCard(modifier: Modifier = Modifier, color: Color = Color(0xFF454164)) {
    Card(colors = CardDefaults.cardColors(
        containerColor = Color.Transparent,
    ), border = BorderStroke(1.dp, Color(0xFF454164)),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 7.dp)
            .height(250.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color),

                ) {
            }
        }

    }
}