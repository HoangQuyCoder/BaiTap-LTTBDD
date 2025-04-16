package com.example.uthsmarttasks.view

import android.app.DatePickerDialog
import android.content.Context
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uthsmarttasks.R
import com.example.uthsmarttasks.model.Task2
import com.example.uthsmarttasks.viewmodel.TaskListViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun AddTaskScreen(navController: NavController, viewModel: TaskListViewModel) {
    val datePickerDialog = createDatePickerDialog { year, month, day ->
        viewModel.updateDueDate(year, month, day)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        AddTaskTopBar(navController)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TaskInputField(
                "Task",
                viewModel.taskTitle,
                viewModel.titleError
            ) {
                viewModel.taskTitle = it
                viewModel.titleError = viewModel.taskTitle.isBlank()
            }

            TaskInputField(
                "Description",
                viewModel.taskDescription,
                viewModel.descriptionError,
                modifier = Modifier.height(120.dp)
            ) {
                viewModel.taskDescription = it
                viewModel.descriptionError = viewModel.taskDescription.isBlank()
            }

            TaskDropdown(
                label = "Status",
                options = listOf("Pending", "In Progress", "Done"),
                selected = viewModel.taskStatus
            ) {
                viewModel.taskStatus = it
            }

            TaskDropdown(
                label = "Priority",
                options = listOf("Low", "Medium", "High"),
                selected = viewModel.taskPriority
            ) {
                viewModel.taskPriority = it
            }

            TaskDropdown(
                label = "Category",
                options = listOf("Personal", "Work", "Study", "Others"),
                selected = viewModel.taskCategory
            ) {
                viewModel.taskCategory = it
            }

            Column {
                Text("Due Date", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = viewModel.taskDueDate,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth(),
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
                    viewModel.addNewTask {
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

@Composable
fun AddTaskTopBar(navController: NavController) {
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
            text = "Add New",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun TaskInputField(
    label: String,
    value: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {
    Column {
        Text(label, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier),
            singleLine = modifier != Modifier.height(120.dp),
            isError = isError,
            label = { if (isError) Text("$label cannot be empty", color = Color.Red) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}

@Composable
fun TaskDropdown(
    label: String,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(label, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Box {
            OutlinedTextField(
                value = selected,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Icon(
                        Icons.Default.ArrowDropDown, contentDescription = null,
                        Modifier.clickable { expanded = !expanded })
                }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            onSelect(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun createDatePickerDialog(
    context: Context = LocalContext.current,
    onDateSelected: (year: Int, month: Int, day: Int) -> Unit
): DatePickerDialog {
    val calendar = Calendar.getInstance()
    return remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                onDateSelected(year, month, dayOfMonth)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
}