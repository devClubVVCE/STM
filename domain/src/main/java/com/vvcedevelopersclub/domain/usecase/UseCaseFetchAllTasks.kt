package com.vvcedevelopersclub.domain.usecase

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.repo.TasksRepository
import com.vvcedevelopersclub.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.util.*

class UseCaseFetchAllTasks(private val tasksRepository: TasksRepository) {
    fun fetchTasks(): Flow<Resource<List<DMTask>>> {
        return tasksRepository.fetchAllTasks()
    }

    fun fetchTaskDates(): Flow<Resource<List<String>>> {
        return tasksRepository.fetchTaskDates()
    }
}