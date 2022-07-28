package com.devclubvvce.domain.usecase

import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.repo.TasksRepository

class UseCaseDeleteTask(private val tasksRepository: TasksRepository) {
    suspend fun deleteTask(dmTask: DMTask) {
        tasksRepository.deleteTask(dmTask)
    }
}