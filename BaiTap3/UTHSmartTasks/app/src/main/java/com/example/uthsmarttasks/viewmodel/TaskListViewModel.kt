package com.example.uthsmarttasks.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class TaskListViewModel(private val repository: TaskRepository) : ViewModel() {
    val tasks: StateFlow<List<Task2>> = repository.allTasks
        .map { it }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults = searchQuery
        .debounce(300)
        .flatMapLatest { query -> repository.search(query) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    private val _taskDetail = MutableStateFlow<Task2?>(null)
    val taskDetail: StateFlow<Task2?> = _taskDetail

    var taskTitle by mutableStateOf("")
    var taskDescription by mutableStateOf("")
    var taskStatus by mutableStateOf("Pending")
    var taskPriority by mutableStateOf("Medium")
    var taskCategory by mutableStateOf("Work")

    var titleError by mutableStateOf(false)
    var descriptionError by mutableStateOf(false)

    private val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var taskDueDate: String by mutableStateOf(formatter.format(Date()))

    fun updateDueDate(year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        taskDueDate = formatter.format(calendar.time)
    }

    fun onTitleChange(newTitle: String) {
        taskTitle = newTitle
        titleError = newTitle.isBlank()
    }

    fun onDescriptionChange(newDesc: String) {
        taskDescription = newDesc
        descriptionError = newDesc.isBlank()
    }

    fun onCategoryChange(newCat: String) {
        taskCategory = newCat
    }

    fun onStatusChange(newStatus: String) {
        taskStatus = newStatus
    }

    fun onPriorityChange(newPriority: String) {
        taskPriority = newPriority
    }

    fun resetTaskForm() {
        taskTitle = ""
        taskDescription = ""
        taskStatus = "Pending"
        taskPriority = "Medium"
        taskCategory = "Work"
        taskDueDate = formatter.format(Date())
        titleError = false
        descriptionError = false
    }

    fun addTask(task: Task2) {
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun addNewTask(onSuccess: () -> Unit) {
        if(isError())
            return

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

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun loadTaskById(taskId: Int) {
        viewModelScope.launch {
            val task = repository.getTaskById(taskId)
            task?.let {
                _taskDetail.value = it
                taskTitle = it.title
                taskDescription = it.description
                taskCategory = it.category
                taskStatus = it.status
                taskPriority = it.priority
                taskDueDate = it.dueDate
                titleError = false
                descriptionError = false
            }
        }
    }

    fun updateTask(task: Task2, onSuccess: () -> Unit) {
        if(isError())
            return

        viewModelScope.launch {
            repository.updateTask(task)
        }

        onSuccess()
    }

    fun isError(): Boolean{
        titleError = taskTitle.isBlank()
        descriptionError = taskDescription.isBlank()
        return titleError || descriptionError
    }

    fun syncFromFirestore() {
        repository.syncTasksFromFirestoreToRoom(clearBeforeInsert = true)
    }
}
