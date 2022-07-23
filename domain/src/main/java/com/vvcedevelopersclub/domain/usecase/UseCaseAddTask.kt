package com.vvcedevelopersclub.domain.usecase

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.repo.TasksRepository

class UseCaseAddTask(private val tasksRepository: TasksRepository) {
    suspend fun addTask(dmTask: DMTask) {
        tasksRepository.addTask(dmTask)
    }
}