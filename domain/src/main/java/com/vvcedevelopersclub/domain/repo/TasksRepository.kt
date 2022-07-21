package com.vvcedevelopersclub.domain.repo

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.util.*

interface TasksRepository {
    fun fetchAllTasks(): Flow<Resource<List<DMTask>>>
    fun fetchTaskDates(): Flow<Resource<List<String>>>
    suspend fun addTask(dmTask: DMTask)
    suspend fun updateTask(dmTask: DMTask)
    suspend fun deleteTask(dmTask: DMTask)
}