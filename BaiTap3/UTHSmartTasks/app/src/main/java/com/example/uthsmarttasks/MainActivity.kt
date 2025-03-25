package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.view.BottomNavItem
import com.example.uthsmarttasks.view.DateScreen
import com.example.uthsmarttasks.view.FirstIntroduction
import com.example.uthsmarttasks.view.HomeScreen
import com.example.uthsmarttasks.view.MenuScreen
import com.example.uthsmarttasks.view.SecondIntroduction
import com.example.uthsmarttasks.view.SettingScreen
import com.example.uthsmarttasks.view.TaskDetailScreen
import com.example.uthsmarttasks.view.ThirdIntroduction
import com.example.uthsmarttasks.view.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationGraph()
        }
    }
}

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("firstIntroduction") {
            FirstIntroduction(navController)
        }
        composable("secondIntroduction") {
            SecondIntroduction(navController)
        }
        composable("thirdIntroduction") {
            ThirdIntroduction(navController)
        }
        composable(BottomNavItem.Home.route) { HomeScreen(navController = navController) }
        composable(BottomNavItem.Document.route) { MenuScreen() }
        composable(BottomNavItem.Setting.route) { (SettingScreen()) }
        composable(BottomNavItem.Date.route) { DateScreen() }

        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            TaskDetailScreen(navController = navController, taskId = taskId)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    NavigationGraph()
}
