package quiz.genai.com.navController

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object Profile : Screens("profile")
}