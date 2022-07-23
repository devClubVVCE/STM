package com.vvcedevelopersclub.data.local.dao

import androidx.room.*
import com.vvcedevelopersclub.data.local.entities.Task
import kotlinx.coroutines.flow.Flow
import java.util.*

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
}