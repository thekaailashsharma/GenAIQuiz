package quiz.genai.com

import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.chart.composed.plus
import com.patrykandpatrick.vico.core.entry.composed.ComposedChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entriesOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import quiz.genai.com.appUsage.TimeTracker
import quiz.genai.com.home.HomeScreen
import quiz.genai.com.navController.BottomBar
import quiz.genai.com.navController.NavController
import quiz.genai.com.navController.Screens
import quiz.genai.com.profile.ProfileScreen
import quiz.genai.com.ui.theme.TryGenAIQuizTheme
import quiz.genai.com.ui.theme.appGradient
import quiz.genai.com.ui.theme.monte
import quiz.genai.com.ui.theme.monteEB
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var timeTracker: TimeTracker

    @OptIn(ExperimentalUnitApi::class, FlowPreview::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        timeTracker = TimeTracker(this)
        // Attach TimeTracker to the activity's lifecycle
        timeTracker.attachToLifecycle(lifecycle)
        timeTracker.startTracking()
        super.onCreate(savedInstanceState)
        setContent {
            val totalTime = remember { timeTracker.getTotalTimeSpent() }
            val navBackStackEntry by rememberNavController().currentBackStackEntryAsState()
            var isBottomBarVisible = remember { mutableStateOf(true) }
            when (navBackStackEntry?.destination?.route) {
                Screens.HomeScreen.route -> {
                    isBottomBarVisible.value = true
                }

                Screens.Profile.route -> {
                    isBottomBarVisible.value = true
                }

                Screens.Jobs.route -> {
                    isBottomBarVisible.value = false
                }
            }
            TryGenAIQuizTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            isBottomBarVisible = isBottomBarVisible
                        )
                    }
                ) {
//                    JobsBoardingScreen()
                    println(it)
//                    ArticleScreen(it)
//                    CourseDetails()
//                    StackedCards()
//                    JobsDetailScreen(
//                        paddingValues = it,
//                        navController = navController,
//                    )
                    NavController(
                        navHostController = navController,
                        paddingValues = it,
                        time = totalTime,
                        timeTracker = timeTracker
                    )
//                    HomeScreen(
//                        time = totalTime,
//                        timeTracker = timeTracker
//                    )
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

    override fun onResume() {
        super.onResume()
        // Resume tracking time when the app is in the foreground
        timeTracker.stopTracking()
    }


    override fun onStop() {
        super.onStop()
        // Stop tracking time when the activity stops
        timeTracker.stopTracking()
    }
}

@OptIn(ObsoleteCoroutinesApi::class)
fun Flow<Long>.refreshEvery(duration: Duration): Flow<Long> = flow {
    ticker(duration.inWholeMilliseconds, duration.inWholeMilliseconds).consumeEach {
        emit(this@refreshEvery.first())
    }
}





