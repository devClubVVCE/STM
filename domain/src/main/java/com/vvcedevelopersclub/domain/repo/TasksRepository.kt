package com.vvcedevelopersclub.domain.repo

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TasksRepository {
    fun fetchAllTasks(): Flow<Resource<List<DMTask>>>
    fun fetchTaskDates(): Flow<Resource<List<String>>>
    fun fetchCompletedTasks(): Flow<Resource<List<DMTask>>>
    fun fetchIncompleteTasks(): Flow<Resource<List<DMTask>>>
    suspend fun fetchTaskById(taskId:Long): DMTask?
    suspend fun addTask(dmTask: DMTask)
    suspend fun deleteTask(dmTask: DMTask)
}