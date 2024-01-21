package quiz.genai.com

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.chart.composed.plus
import com.patrykandpatrick.vico.core.entry.composed.ComposedChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entriesOf
import dagger.hilt.android.AndroidEntryPoint
import quiz.genai.com.appUsage.TimeTracker
import quiz.genai.com.home.HomeScreen
import quiz.genai.com.ui.theme.TryGenAIQuizTheme
import quiz.genai.com.ui.theme.appGradient
import quiz.genai.com.ui.theme.monte
import quiz.genai.com.ui.theme.monteEB

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var timeTracker: TimeTracker
    @OptIn(ExperimentalUnitApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        timeTracker = TimeTracker(this)
        // Attach TimeTracker to the activity's lifecycle
        timeTracker.attachToLifecycle(lifecycle)
        timeTracker.startTracking()
        super.onCreate(savedInstanceState)
        setContent {
            val totalTime = remember { timeTracker.getTotalTimeSpent() }
            TryGenAIQuizTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        time = totalTime,
                        timeTracker = timeTracker
                    )
//                    val composedChartEntryModelProducer = ComposedChartEntryModelProducer.build {
//                        add(entriesOf(4f, 12f, 8f, 16f))
//                        add(entriesOf(16f, 8f, 12f, 4f))
//                    }
//
//                    val columnChart = columnChart()
//                    val lineChart = lineChart()
//                    Chart(
//                        chart = remember(columnChart, lineChart) { columnChart + lineChart },
//                        chartModelProducer = composedChartEntryModelProducer,
//                        startAxis = rememberStartAxis(),
//                        bottomAxis = rememberBottomAxis(),
//                    )



//                    Column(
//                        modifier = Modifier.fillMaxSize(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp), contentAlignment = Alignment.Center) {
//                            RotatedSkillsCard(
//                                modifier = Modifier
//                                    .graphicsLayer(
//                                        rotationZ = 348f,
//                                        transformOrigin = TransformOrigin(
//                                            pivotFractionX = 0f, // Set pivot at the left-most border
//                                            pivotFractionY = 1f // Set pivot at the bottom-left corner
//                                        )
//                                    ),
//                                color = Color(0xFFF5F378).copy(0.85f)
//                            )
//                            RotatedSkillsCard(
//                                modifier = Modifier
//                                    .graphicsLayer(
//                                        rotationZ = 354f,
//                                        transformOrigin = TransformOrigin(
//                                            pivotFractionX = 0f, // Set pivot at the left-most border
//                                            pivotFractionY = 1f // Set pivot at the bottom-left corner
//                                        )
//                                    ),
//                                color = Color(0xFFDCC1FF).copy(0.85f)
//
//                            )
//                            SkillsCard(
//                                modifier = Modifier
//                                    .graphicsLayer(
//                                        rotationZ = 0f, // No rotation for the third card
//                                        transformOrigin = TransformOrigin(
//                                            pivotFractionX = 0f, // Set pivot at the left-most border
//                                            pivotFractionY = 1f // Set pivot at the bottom-left corner
//                                        )
//                                    )
//                            )
//                        }
//                    }
//                    val viewModel: QuizViewModel = hiltViewModel()
//                    DemoQuiz(viewModel = viewModel)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // Start tracking time when the activity starts
    }

    override fun onPause() {
        super.onPause()
        // Stop tracking time when the activity pauses
        timeTracker.stopTracking()
    }

    override fun onStop() {
        super.onStop()
        // Stop tracking time when the activity stops
        timeTracker.stopTracking()
    }
}


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

@Composable
fun RotatedSkillsCard2(modifier: Modifier = Modifier) {
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
                    .background(appGradient),

                ) {
            }
        }

    }
}
@Preview(showBackground = false,)
@Composable
fun DefaultPreview() {
    TryGenAIQuizTheme {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(500.dp), contentAlignment = Alignment.Center) {
                RotatedSkillsCard2(
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = 348f,
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0f, // Set pivot at the left-most border
                                pivotFractionY = 1f // Set pivot at the bottom-left corner
                            )
                        )
                )
                RotatedSkillsCard2(
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = 354f,
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0f, // Set pivot at the left-most border
                                pivotFractionY = 1f // Set pivot at the bottom-left corner
                            )
                        )
                )
                SkillsCard(
                    modifier = Modifier
                        .graphicsLayer(
                            rotationZ = 0f, // No rotation for the third card
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0f, // Set pivot at the left-most border
                                pivotFractionY = 1f // Set pivot at the bottom-left corner
                            )
                        )
                )
            }
        }
}

