package com.example.uthsmarttasks.view

import android.R.attr.category
import android.R.attr.description
import android.R.attr.priority
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.model.Task2
import com.example.uthsmarttasks.viewmodel.TaskListViewModel

@SuppressLint("DefaultLocale")
@Composable
fun UpdateTaskScreen(
    taskId: Int,
    navController: NavController,
    viewModel: TaskListViewModel
) {
    val task by viewModel.taskDetail.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.loadTaskById(taskId)
    }

    task?.let { currentTask ->
        val title = viewModel.taskTitle
        val description = viewModel.taskDescription
        val category = viewModel.taskCategory
        val status = viewModel.taskStatus
        val priority = viewModel.taskPriority
        val dueDate = viewModel.taskDueDate

        val datePickerDialog = createDatePickerDialog { year, month, day ->
            viewModel.updateDueDate(year, month, day)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
        ) {
            EditTaskTopBar(navController)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TaskInputField(
                    label = "Task",
                    value = title,
                    isError = viewModel.titleError,
                    onValueChange = viewModel::onTitleChange
                )

                TaskInputField(
                    label = "Description",
                    value = description,
                    isError = viewModel.descriptionError,
                    modifier = Modifier.height(120.dp),
                    onValueChange = viewModel::onDescriptionChange
                )

                TaskDropdown(
                    label = "Status",
                    options = listOf("Pending", "In Progress", "Done"),
                    selected = status,
                    onSelected = viewModel::onStatusChange
                )

                TaskDropdown(
                    label = "Priority",
                    options = listOf("Low", "Medium", "High"),
                    selected = priority,
                    onSelected = viewModel::onPriorityChange
                )

                TaskDropdown(
                    label = "Category",
                    options = listOf("Personal", "Work", "Study", "Others"),
                    selected = category,
                    onSelected = viewModel::onCategoryChange
                )

                Column {
                    Text("Due Date", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = dueDate,
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Pick Date",
                                modifier = Modifier.clickable { datePickerDialog.show() }
                            )
                        },
                        textStyle = TextStyle(fontSize = 16.sp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        val updatedTask = Task2(
                            id = currentTask.id,
                            title = title,
                            description = description,
                            status = status,
                            priority = priority,
                            category = category,
                            dueDate = dueDate
                        )
                        viewModel.updateTask(updatedTask){
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Update", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}


@Composable
fun EditTaskTopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.size(40.dp),
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
            text = "Edit Task",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

