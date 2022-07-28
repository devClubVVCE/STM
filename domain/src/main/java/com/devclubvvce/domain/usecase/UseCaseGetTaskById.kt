package com.devclubvvce.domain.usecase

import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.repo.TasksRepository

class UseCaseGetTaskById(private val tasksRepository: TasksRepository) {
    suspend fun getTask(taskId:Long):DMTask? {
        return tasksRepository.fetchTaskById(taskId)
    }
}