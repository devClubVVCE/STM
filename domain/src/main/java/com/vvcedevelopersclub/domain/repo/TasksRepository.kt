package com.vvcedevelopersclub.domain.repo

import com.vvcedevelopersclub.domain.models.DMTask
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun fetchAllTasks(): Flow<List<DMTask>>
}