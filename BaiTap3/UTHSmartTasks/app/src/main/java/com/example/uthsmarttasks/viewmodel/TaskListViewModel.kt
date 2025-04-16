package com.example.uthsmarttasks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uthsmarttasks.model.Task2
import com.example.uthsmarttasks.model.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {

    var taskTitle by mutableStateOf("")
    var taskDescription by mutableStateOf("")
    var taskStatus by mutableStateOf("Pending")
    var taskPriority by mutableStateOf("Medium")
    var taskCategory by mutableStateOf("Work")

    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var taskDueDate: String by mutableStateOf(formatter.format(Date()))

    var titleError by mutableStateOf(false)
    var descriptionError  by mutableStateOf(false)

    fun updateDueDate(year: Int, month: Int, day: Int) {
        taskDueDate = "$day/${month + 1}/$year"
    }

    fun resetTaskForm() {
        taskTitle = ""
        taskDescription = ""
        taskStatus = "Pending"
        taskPriority = "Medium"
        taskCategory = "Work"
        taskDueDate = formatter.format(Date())
        titleError = false
    }

    val tasks: StateFlow<List<Task2>> = repository.allTasks
        .map { it }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTask(task: Task2) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun addNewTask(onSuccess: () -> Unit) {
        titleError = taskTitle.isBlank()
        descriptionError = taskDescription.isBlank()

        if (titleError || descriptionError) return

        val newTask = Task2(
            title = taskTitle,
            description = taskDescription,
            status = taskStatus,
            priority = taskPriority,
            category = taskCategory,
            dueDate = taskDueDate
        )

        addTask(newTask)
        onSuccess()
    }

    fun deleteTaskById(taskId: Int) {
        viewModelScope.launch {
            repository.deleteTaskById(taskId)
        }
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = searchQuery
        .debounce(300)
        .flatMapLatest { query -> repository.search(query) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private val _taskDetail = MutableStateFlow<Task2?>(null)
    val taskDetail: StateFlow<Task2?> = _taskDetail

    fun loadTaskById(taskId: Int) {
        viewModelScope.launch {
            val task = repository.getTaskById(taskId)
            _taskDetail.value = task
        }
    }

    fun updateTask(task: Task2) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

}

