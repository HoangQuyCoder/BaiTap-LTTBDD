package com.example.uthsmarttasks.view

import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.model.Task
import com.example.uthsmarttasks.model.Task2
import com.example.uthsmarttasks.viewmodel.TaskDetailViewModel
import com.example.uthsmarttasks.viewmodel.TaskListViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TaskListDetailScreen(
    viewModel: TaskListViewModel,
    navController: NavController,
    taskId: Int
) {
    val taskDetail = viewModel.taskDetail.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.loadTaskById(taskId)
    }

    Scaffold(
        topBar = {
            TaskListDetailTopBar(navController) {
                viewModel.deleteTaskById(taskId)
                navController.popBackStack()
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                taskDetail.value.let { task ->
                    Text(
                        text = task?.title ?: "Dữ liệu đang cập nhật...",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = task?.description ?: "Dữ liệu đang cập nhật...",
                        fontSize = 14.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Info category, status, priority
                    TaskInfoSection(taskDetail.value)

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        DetailRow(label = "Status", value = task?.status ?: "")
                        DetailRow(label = "Deadline", value = task?.dueDate ?: "")
                    }

                }
            }
        }
    )
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", fontWeight = FontWeight.Medium, fontSize = 16.sp)
        Text(text = value,fontWeight = FontWeight.Medium, color = Color.Gray)
    }
}

@Composable
fun TaskInfoSection(task: Task2?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(rgb(225, 187, 193)),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.category),
                contentDescription = "Category",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )

            InfoCard(title = "Category", value = task?.category ?: "")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.status),
                contentDescription = "Status",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )

            InfoCard(title = "Status", value = task?.status ?: "")

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.priority),
                contentDescription = "Priority",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )

            InfoCard(
                title = "Priority",
                value = task?.priority ?: "",
            )

        }
    }
}

@Composable
fun TaskListDetailTopBar(navController: NavController, deleteTask: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

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
            text = "Detail",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center
        )

        // Nút delete
        IconButton(onClick = {showDialog = true}) {
            Icon(
                imageVector = Icons.Default.Delete,
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

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirm Deletion") },
                text = { Text("Are you sure you want to delete this task?") },
                confirmButton = {
                    TextButton(onClick = {
                        deleteTask()
                        showDialog = false
                    }) {
                        Text("Delete", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}