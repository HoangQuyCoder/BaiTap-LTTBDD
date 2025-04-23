package com.example.uthsmarttasks.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_table")
    fun getAllTasks(): Flow<List<Task2>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task2)

    @Query("DELETE FROM task_table WHERE id = :taskId")
    suspend fun deleteById(taskId: Int)

    @Query("SELECT * FROM task_table WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchTasks(query: String): Flow<List<Task2>>

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    suspend fun getTaskById(taskId: Int): Task2?

    @Update
    suspend fun update(task: Task2)

    @Query("DELETE FROM task_table")
    suspend fun clearAll()

}