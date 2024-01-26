package quiz.genai.com.navController

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import quiz.genai.com.ArticleScreen
import quiz.genai.com.CourseDetails
import quiz.genai.com.DemoQuiz
import quiz.genai.com.JobsBoardingScreen
import quiz.genai.com.QuizViewModel
import quiz.genai.com.appUsage.TimeTracker
import quiz.genai.com.home.HomeScreen
import quiz.genai.com.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavController(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    time: Long,
    timeTracker: TimeTracker,
) {
    val viewModel = hiltViewModel<QuizViewModel>()
    NavHost(
        navController = navHostController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(time = time, timeTracker = timeTracker, paddingValues, navHostController)
        }
        composable(Screens.Profile.route) {
            ProfileScreen(paddingValues)
        }

        composable(Screens.Jobs.route) {
            JobsBoardingScreen(paddingValues)
        }

        composable(Screens.ArticleScreen.route) {
            ArticleScreen(paddingValues)
        }

        composable(Screens.CourseDetails.route) {
            CourseDetails(paddingValues, navHostController, viewModel)
        }

        composable(Screens.QuizScreen.route) {
            DemoQuiz(viewModel = viewModel, paddingValues)
        }
    }
}