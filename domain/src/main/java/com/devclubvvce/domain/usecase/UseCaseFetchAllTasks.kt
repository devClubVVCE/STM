package com.devclubvvce.domain.usecase

import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.repo.TasksRepository
import com.devclubvvce.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class UseCaseFetchAllTasks(private val tasksRepository: TasksRepository) {
    fun fetchTasks(): Flow<Resource<List<DMTask>>> {
        return tasksRepository.fetchAllTasks()
    }

    fun fetchTaskDates(): Flow<Resource<List<String>>> {
        return tasksRepository.fetchTaskDates()
    }

    fun fetchCompleteTasks(): Flow<Resource<List<DMTask>>> {
        return tasksRepository.fetchCompletedTasks()
    }

    fun fetchIncompleteTasks(): Flow<Resource<List<DMTask>>> {
        return tasksRepository.fetchIncompleteTasks()
    }
}