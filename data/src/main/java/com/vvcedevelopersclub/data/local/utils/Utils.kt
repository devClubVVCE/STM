package com.vvcedevelopersclub.data.local.utils

import com.vvcedevelopersclub.data.local.entities.Task
import com.vvcedevelopersclub.domain.models.DMTask

object Utils {

    fun Task.toDMTask(): DMTask {
        return DMTask(
            taskName = this.taskName,
            taskDescription = this.taskDescription,
            taskTime = this.taskTime,
            taskDate = this.taskDate,
            taskId = this.taskId,
            isTaskCompleted = this.isTaskCompleted,
        )
    }
}