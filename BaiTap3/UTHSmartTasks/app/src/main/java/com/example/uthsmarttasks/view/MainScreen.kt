package com.example.uthsmarttasks.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.uthsmarttasks.viewmodel.TaskViewModel

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
               HomeScreen(navController = navController)
            }
        }
    )
}
