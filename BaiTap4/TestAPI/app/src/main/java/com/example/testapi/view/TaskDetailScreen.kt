package com.example.testapi.view

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.data.model.Attachment
import com.example.testapi.data.model.Subtask
import com.example.testapi.viewmodel.TaskDetailViewModel

@Composable
fun TaskDetailScreen(
    viewModel: TaskDetailViewModel = viewModel(),
    navController: NavController,
    taskId: String
) {
    val taskDetail = viewModel.taskDetail.collectAsState()
    LaunchedEffect(taskId) {
        viewModel.fetchTaskById(taskId)
    }
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
                onClick = { navController.popBackStack() },
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
                text = "Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            taskDetail.value.let { task ->
                // Task Title
                Text(
                    text = task?.title.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                // Task Description
                Text(
                    text = task?.description.toString(),
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Subtasks
                Text(
                    text = "Subtasks",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                task?.subtasks?.forEach { subtask ->
                    SubtaskItem(subtask)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Attachments
                Text(
                    text = "Attachments",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                task?.attachments?.forEach { attachment ->
                    AttachmentItem(attachment)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun SubtaskItem(subtask: Subtask) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Text(
            text = subtask.title,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}

@Composable
fun AttachmentItem(attachment: Attachment) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        Text(
            text = attachment.fileName,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}