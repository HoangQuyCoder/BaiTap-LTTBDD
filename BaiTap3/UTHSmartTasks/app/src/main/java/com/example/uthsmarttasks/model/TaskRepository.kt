package com.example.uthsmarttasks.model

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao) {

    private val firestore = FirebaseFirestore.getInstance()
    val allTasks: Flow<List<Task2>> = taskDao.getAllTasks()

    // Insert
    suspend fun insert(task: Task2) {
        taskDao.insertTask(task)
//        uploadTaskToFirestore(task)
    }

    // Update
    suspend fun updateTask(task: Task2) {
        taskDao.update(task)
//        uploadTaskToFirestore(task)
    }

    // Delete
    suspend fun deleteTaskById(taskId: Int) {
        taskDao.deleteById(taskId)
    }

    // Get task by id
    suspend fun getTaskById(taskId: Int): Task2? {
        return taskDao.getTaskById(taskId)
    }

    // Search
    fun search(query: String): Flow<List<Task2>> = taskDao.searchTasks(query)

    // --- Firestore methods ---
    private fun uploadTaskToFirestore(task: Task2) {
        firestore.collection("tasks")
            .document(task.id.toString())
            .set(task)
            .addOnSuccessListener {
                Log.d("Firestore", "Uploaded task ${task.id}")
            }
            .addOnFailureListener {
                Log.e("Firestore", "Failed to upload task ${task.id}: ${it.message}")
            }
    }

    private fun deleteTaskFromFirestore(taskId: Int) {
        firestore.collection("tasks")
            .document(taskId.toString())
            .delete()
            .addOnSuccessListener {
                Log.d("Firestore", "Deleted task $taskId")
            }
            .addOnFailureListener {
                Log.e("Firestore", "Failed to delete task $taskId: ${it.message}")
            }
    }

    fun syncTasksFromFirestoreToRoom(clearBeforeInsert: Boolean = false) {
        firestore.collection("tasks")
            .get()
            .addOnSuccessListener { result ->
                val tasks = result.mapNotNull { it.toObject(Task2::class.java) }

                CoroutineScope(Dispatchers.IO).launch {
                    if (clearBeforeInsert) {
                        taskDao.clearAll()
                    }

                    tasks.forEach { task ->
                        try {
                            taskDao.insertTask(task) // đảm bảo @Insert có onConflict xử lý
                        } catch (e: Exception) {
                            Log.e("RoomInsert", "Insert failed: ${e.message}")
                        }
                    }
                }
            }
            .addOnFailureListener {
                Log.e("FirestoreSync", "Sync failed: ${it.message}")
            }
    }
}


