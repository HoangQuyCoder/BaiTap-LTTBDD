package com.example.uthsmarttasks.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uthsmarttasks.viewmodel.TaskListViewModel

@Suppress("UNCHECKED_CAST")
class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel(repository) as T
    }
}
