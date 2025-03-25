package com.example.navigationlazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationlazy.view.DetailScreen
import com.example.navigationlazy.view.QuoteListScreen
import com.example.navigationlazy.view.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Controller()
        }
    }
}

@Composable
fun Controller() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("components") { QuoteListScreen(navController) }
        composable("detail/{title}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "Detail"
            DetailScreen(title, navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    Controller()
}
