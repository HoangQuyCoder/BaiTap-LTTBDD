package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.uthsmarttasks.model.TaskDatabase
import com.example.uthsmarttasks.model.TaskRepository
import com.example.uthsmarttasks.model.TaskViewModelFactory
import com.example.uthsmarttasks.view.AddTaskScreen
import com.example.uthsmarttasks.model.BottomNavItem
import com.example.uthsmarttasks.view.DateScreen
import com.example.uthsmarttasks.view.DocumentScreen
import com.example.uthsmarttasks.view.FirstIntroduction
import com.example.uthsmarttasks.view.HomeScreen
import com.example.uthsmarttasks.view.LoginScreen
import com.example.uthsmarttasks.view.ProfileScreen
import com.example.uthsmarttasks.view.SecondIntroduction
import com.example.uthsmarttasks.view.SettingScreen
import com.example.uthsmarttasks.view.TaskDetailScreen
import com.example.uthsmarttasks.view.TaskListDetailScreen
import com.example.uthsmarttasks.view.ThirdIntroduction
import com.example.uthsmarttasks.view.UpdateTaskScreen
import com.example.uthsmarttasks.view.WelcomeScreen
import com.example.uthsmarttasks.viewmodel.TaskListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        val db = TaskDatabase.getDatabase(this)
        val repository = TaskRepository(db.taskDao())
        val viewModel = ViewModelProvider(
            this,
            TaskViewModelFactory(repository)
        )[TaskListViewModel::class.java]

        setContent {
            NavigationGraph(viewModel)
        }
    }
}

@Composable
fun NavigationGraph(viewModel: TaskListViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = BottomNavItem.Document.route) {
        composable("welcome") { WelcomeScreen(navController) }
        composable("firstIntroduction") { FirstIntroduction(navController) }
        composable("secondIntroduction") { SecondIntroduction(navController) }
        composable("thirdIntroduction") { ThirdIntroduction(navController) }

        composable(BottomNavItem.Home.route) { HomeScreen(navController = navController) }
        composable(BottomNavItem.Document.route) { DocumentScreen(navController, viewModel) }
        composable(BottomNavItem.Setting.route) { (SettingScreen(navController)) }
        composable(BottomNavItem.Date.route) { DateScreen(navController) }

        composable("detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            TaskDetailScreen(navController = navController, taskId = taskId)
        }

        composable("login") { LoginScreen(navController) }
        composable("profile") { ProfileScreen(navController) }

        composable("add_task") { AddTaskScreen(navController, viewModel) }

        composable("task_detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            TaskListDetailScreen(
                viewModel = viewModel,
                navController = navController,
                taskId = taskId
            )
        }

        composable("update/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull() ?: 0
            UpdateTaskScreen(taskId = taskId, navController = navController, viewModel = viewModel)
        }

    }
}
