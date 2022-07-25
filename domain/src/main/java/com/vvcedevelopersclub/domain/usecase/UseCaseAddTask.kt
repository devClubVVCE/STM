package com.vvcedevelopersclub.domain.usecase

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.models.InvalidTaskException
import com.vvcedevelopersclub.domain.repo.TasksRepository

class UseCaseAddTask(private val tasksRepository: TasksRepository) {
    suspend fun addTask(dmTask: DMTask) {
        when {
            dmTask.taskDate.isEmpty() -> {
                throw InvalidTaskException("Please select the task date")
            }
            dmTask.taskTime.isEmpty() -> {
                throw InvalidTaskException("Please select the task time")
            }
            dmTask.taskName.isEmpty() -> {
                throw InvalidTaskException("The title of the task can't be empty")
            }
            dmTask.taskDescription.isEmpty() -> {
                throw InvalidTaskException("The description of the task can't be empty")
            }
            else -> tasksRepository.addTask(dmTask)
        }
    }
}