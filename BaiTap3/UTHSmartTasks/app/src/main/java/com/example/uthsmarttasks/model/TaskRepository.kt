package com.example.uthsmarttasks.model

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task2>> = taskDao.getAllTasks()

    suspend fun insert(task: Task2) {
        taskDao.insertTask(task)
    }

    fun search(query: String): Flow<List<Task2>> = taskDao.searchTasks(query)

    suspend fun deleteTaskById(taskId: Int) {
        taskDao.deleteById(taskId)
    }

    suspend fun getTaskById(taskId: Int): Task2? {
        return taskDao.getTaskById(taskId)
    }

    suspend fun updateTask(task: Task2) {
        taskDao.update(task)
    }

//    suspend fun insertIfNotExists(title: String, description: String, color: Long): Boolean {
//        val exists = taskDao.findTask(title, description) != null
//        return if (!exists && title.isNotBlank() && description.isNotBlank()) {
//            taskDao.insertTask(Task2(title = title, description = description, color =  color))
//            true
//        } else false
//    }
}
