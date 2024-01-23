package quiz.genai.com.navController


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import quiz.genai.com.ui.theme.buttonBackground
import quiz.genai.com.ui.theme.lightGray
import quiz.genai.com.ui.theme.textColor

@Composable
fun BottomBar(
    navController: NavController,
    isBottomBarVisible: MutableState<Boolean> = mutableStateOf(true),
) {
    AnimatedVisibility(
        visible = isBottomBarVisible.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, bottom = 15.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = textColor
            ),
            elevation = CardDefaults.cardElevation(0.dp),
            shape = RoundedCornerShape(40.dp),
            border = BorderStroke(1.dp, color = lightGray)
        ) {
            Divider(thickness = 1.dp, color = textColor.copy(0.5f))
            NavigationBar(
                modifier = Modifier
                    .height(60.dp),
                containerColor = Color.Transparent,
                tonalElevation = 0.dp,
            ) {
                items.forEach {
                    val selected = currentRoute?.hierarchy?.any { nav ->
                        nav.route == it.route
                    } == true
                    NavigationBarItem(
                        icon = {
                            it.icon?.let { it1 ->
                                Icon(
                                    imageVector = it1,
                                    contentDescription = null,
                                    tint = textColor.copy(0.9f)
                                )
                            }
                        },
                        selected = selected,
                        onClick = {
                            it.route?.let { it1 ->
                                navController.navigate(it1) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = textColor.copy(0.9f),
                            indicatorColor = buttonBackground,
                        )
                    )
                }
            }
        }
    }
}