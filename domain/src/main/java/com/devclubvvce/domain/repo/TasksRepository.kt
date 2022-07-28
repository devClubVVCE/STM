package com.devclubvvce.domain.repo

import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun fetchAllTasks(): Flow<Resource<List<DMTask>>>
    fun fetchTaskDates(): Flow<Resource<List<String>>>
    fun fetchCompletedTasks(): Flow<Resource<List<DMTask>>>
    fun fetchIncompleteTasks(): Flow<Resource<List<DMTask>>>
    suspend fun fetchTaskById(taskId:Long): DMTask?
    suspend fun addTask(dmTask: DMTask)
    suspend fun deleteTask(dmTask: DMTask)
}