package com.example.uthsmarttasks.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.NavigationGraph
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.model.Task2
import com.example.uthsmarttasks.viewmodel.TaskListViewModel

@Composable
fun AddTaskScreen(navController: NavController, viewModel: TaskListViewModel) {

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color(0xFFFFB6C1).value) }
    var titleError by remember { mutableStateOf(false) }
    val tasks by viewModel.tasks.collectAsState()

    // Danh sách màu để người dùng chọn
    val colorOptions = listOf(
        Color(0xFFADD8E6), // Light Blue
        Color(0xFFFFB6C1), // Light Pink
        Color(0xFF90EE90), // Light Green
        Color(0xFFFFFACD), // Light Yellow
        Color(0xFFDDA0DD)  // Light Purple
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Button(
                onClick = {navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopStart),
                colors = ButtonDefaults.buttonColors(Color(0xFF2196F3)),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Text(
                text = "Add New",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Task Title Input
            Text("Task", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                    titleError = taskTitle.isBlank() // Kiểm tra lỗi khi nhập
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                isError = titleError,
                label = { if (titleError) Text("Task cannot be empty", color = Color.Red) },
                textStyle = TextStyle(fontSize = 16.sp)
            )

            // Task Description Input
            Text("Description", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontSize = 16.sp)
            )

            // Color Selection
            Text("Select Color", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                colorOptions.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color)
                            .clickable {
                                selectedColor = color.value // Cập nhật màu được chọn
                            }
                            .then(
                                if (selectedColor == color.value) {
                                    Modifier.border(2.dp, Color.Black, CircleShape)
                                } else {
                                    Modifier
                                }
                            )
                    )
                }
            }

            // Add Button
            Button(
                onClick = {
                    if (taskTitle.isBlank()) {
                        titleError = true
                    } else {
                        val newTask = Task2(
                            id = (tasks.size + 1),
                            title = taskTitle,
                            description = taskDescription,
                            color = selectedColor
                        )
                        println("AddTaskScreen: Adding new task: $newTask")
                        viewModel.addTask(newTask)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Add", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    NavigationGraph()
}

