package com.example.testapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapi.data.model.Task
import com.example.testapi.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class TaskDetailViewModel() : ViewModel() {
    private val _taskDetail = MutableStateFlow<Task?>(null)
    val taskDetail: StateFlow<Task?> = _taskDetail

    fun fetchTaskById(taskId: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTaskById(taskId)
                val taskResponse = response.body()
                if (taskResponse?.isSuccess == true) {
                    _taskDetail.value = taskResponse.data
                }
            } catch (e: Exception) {
                println("Exception555: ${e.message}")
            }
        }
    }
}
