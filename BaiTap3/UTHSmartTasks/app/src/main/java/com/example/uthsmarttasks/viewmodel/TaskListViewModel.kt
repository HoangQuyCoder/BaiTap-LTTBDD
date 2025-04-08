package com.example.uthsmarttasks.viewmodel

import androidx.lifecycle.ViewModel
import com.example.uthsmarttasks.model.Task2
import com.example.uthsmarttasks.view.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskListViewModel(
    private val repository: TaskRepository = TaskRepository()
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task2>>(emptyList())
    val tasks: StateFlow<List<Task2>> = _tasks

    init {
        _tasks.value = repository.getTasks()
    }

    fun addTask(task: Task2) {
        repository.addTask(task)
        _tasks.value = repository.getTasks()

        println("Task added: $task, new list: ${_tasks.value}")
    }
}


