package com.vvcedevelopersclub.domain.usecase

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.repo.TasksRepository

class UseCaseDeleteTask(private val tasksRepository: TasksRepository) {
    suspend fun deleteTask(dmTask: DMTask) {
        tasksRepository.deleteTask(dmTask)
    }
}