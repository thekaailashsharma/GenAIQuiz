package quiz.genai.com.navController

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import quiz.genai.com.R


sealed class BottomBarScreens(val route: String?, val title: String?, val icon: ImageVector?) {
    object HomeScreen : BottomBarScreens(Screens.HomeScreen.route, "Home", Icons.Filled.Home)

    object Profile :
        BottomBarScreens(Screens.Profile.route, "Community", Icons.Filled.Person)
}

val items = listOf(
    BottomBarScreens.HomeScreen,
    BottomBarScreens.Profile
)