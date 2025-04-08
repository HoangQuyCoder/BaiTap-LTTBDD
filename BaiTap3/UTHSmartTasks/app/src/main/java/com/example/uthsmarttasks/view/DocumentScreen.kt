package com.example.uthsmarttasks.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.model.Task2
import com.example.uthsmarttasks.viewmodel.TaskListViewModel

class TaskRepository {
    private val taskList = mutableListOf(
        Task2(
            id = 1,
            title = "Buy Groceries",
            description = "Milk, Eggs, Bread, Fruits",
            color = Color(0xFFFFB6C1).value
        ),
        Task2(
            id = 2,
            title = "Project Meeting",
            description = "Discuss project timeline and deliverables",
            color = Color(0xFF90EE90).value
        ),
        Task2(
            id = 3,
            title = "Gym Session",
            description = "Leg day workout at 6 PM",
            color =  Color(0xFFFFB6C1).value
        )
    )

    fun getTasks(): List<Task2> = taskList

    fun addTask(task: Task2) {
        taskList.add(task)
    }
}

@Composable
fun DocumentScreen(navController: NavController, viewModel: TaskListViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    println("TaskApp: Current tasks = $tasks")

    Scaffold(
        topBar = {
            TaskListTopBar(navController)
        },
        bottomBar = { BottomNavBar(navController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding((paddingValues))
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                ) { items(tasks) { task -> TaskItem(task = task) } }
            }
        }
    )
}

@Composable
fun TaskItem(task: Task2) {
    Card(
        modifier = Modifier
            .fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(task.color)
        ),
    ) {

        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = task.description, fontSize = 16.sp)
        }

    }
}

@Composable
fun TaskListTopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nút back
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                painter = painterResource(R.drawable.arrow_back2),
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF2196F3), shape = RoundedCornerShape(10.dp))
                    .padding(8.dp)
            )
        }

        // Tiêu đề
        Text(
            text = "List",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center
        )

        IconButton(onClick = {navController.navigate("add_task")}) {
            Icon(
                imageVector = Icons.Default.AddCircleOutline,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFFA726), Color(0xFFD84315))
                        ),
                        shape = CircleShape
                    )
                    .padding(8.dp)
            )
        }
    }
}

