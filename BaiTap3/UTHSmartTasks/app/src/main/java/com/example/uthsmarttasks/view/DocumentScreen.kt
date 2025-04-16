package com.example.uthsmarttasks.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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

@Composable
fun DocumentScreen(navController: NavController, viewModel: TaskListViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    Scaffold(
        topBar = {
            TaskListTopBar(
                navController = navController,
                searchQuery = searchQuery,
                onSearchChange = { viewModel.updateSearchQuery(it) }
            )
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
                ) {
                    items(searchResults) { task ->
                        TaskItem(
                            navController = navController,
                            task = task,
                            onClick = { navController.navigate("task_detail/${task.id}") }
                        )

                    }
                }
            }
        }
    )
}

@Composable
fun TaskItem(navController: NavController, task: Task2, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (task.priority) {
                "High" -> Color(0xFFFFCDD2)
                "Medium" -> Color(0xFFFFFF99)
                "Low" -> Color(0xFFBBDEFB)
                else -> Color.White
            }
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.padding(4.dp))
                Text(text = task.description, fontSize = 18.sp)
            }

            IconButton(onClick = { navController.navigate("update/${task.id}") }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Status: " + task.status, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = task.dueDate, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun TaskListTopBar(
    navController: NavController,
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
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

            IconButton(onClick = { navController.navigate("add_task") }) {
                Icon(
                    imageVector = Icons.Default.AddCircleOutline,
                    contentDescription = "Add",
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

        // Search bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text("Search tasks...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
    }
}


