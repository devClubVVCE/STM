package com.vvcedevelopersclub.domain.usecase

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.models.InvalidTaskException
import com.vvcedevelopersclub.domain.repo.TasksRepository

class UseCaseGetTaskById(private val tasksRepository: TasksRepository) {
    suspend fun getTask(taskId:Long):DMTask? {
        return tasksRepository.fetchTaskById(taskId)
    }
}