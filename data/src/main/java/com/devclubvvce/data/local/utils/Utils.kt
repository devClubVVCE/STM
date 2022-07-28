package com.devclubvvce.data.local.utils

import com.devclubvvce.data.local.entities.Task
import com.devclubvvce.domain.models.DMTask
import java.text.SimpleDateFormat
import java.util.*

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

    fun DMTask.toTask(): Task {
        return Task(
            this.taskName,
            this.taskDescription,
            this.taskTime,
            this.taskDate,
            this.isTaskCompleted,
            this.taskId,
        )
    }

    fun getCurrentDate(): String {
        return SimpleDateFormat("dd/MMM/yyyy", Locale.US).format(Date())
    }

    fun getPreviousDay(): String {
        return SimpleDateFormat(
            "dd/MMM/yyyy",
            Locale.US
        ).format(System.currentTimeMillis() - (1000 * 60 * 60 * 24))
    }

    fun getNextDay(): String {
        return SimpleDateFormat(
            "dd/MMM/yyyy",
            Locale.US
        ).format(System.currentTimeMillis() + (1000 * 60 * 60 * 24))
    }
}