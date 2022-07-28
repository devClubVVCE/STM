package com.devclubvvce.data.local.dao

import androidx.room.*
import com.devclubvvce.data.local.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM Task ORDER BY taskDate DESC")
    fun fetchTasks(): Flow<List<Task>>

    @Query("SELECT DISTINCT taskDate FROM Task ORDER BY taskDate DESC")
    fun fetchTaskDates(): Flow<List<String>>

    @Query("SELECT * FROM Task WHERE taskId = :id")
    suspend fun getTaskById(id: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM Task WHERE isTaskCompleted = 1 ORDER BY taskDate DESC")
    fun fetchCompletedTasks(): Flow<List<Task>>

    @Query("SELECT * FROM Task WHERE isTaskCompleted = 0 ORDER BY taskDate DESC")
    fun fetchIncompleteTasks(): Flow<List<Task>>
}