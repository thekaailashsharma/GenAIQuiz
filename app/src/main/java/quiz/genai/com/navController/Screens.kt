package quiz.genai.com.navController

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object Profile : Screens("profile")
    object Jobs : Screens("jobs")
    object ArticleScreen : Screens("article")
    object CourseDetails : Screens("courseDetails")
    object QuizScreen : Screens("quizScreen")
    object JobsDetailScreen : Screens("jobsDetailScreen")
}