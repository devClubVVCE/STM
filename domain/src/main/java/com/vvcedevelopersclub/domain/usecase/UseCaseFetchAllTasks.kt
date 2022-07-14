package com.vvcedevelopersclub.domain.usecase

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.repo.TasksRepository
import kotlinx.coroutines.flow.Flow

class UseCaseFetchAllTasks(private val tasksRepository: TasksRepository) {
    fun fetchTasks(): Flow<List<DMTask>> {
        return tasksRepository.fetchAllTasks()
    }
}