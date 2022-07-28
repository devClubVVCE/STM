package com.devclubvvce.domain.models


data class DMTask(
    val taskName: String,
    val taskDescription: String,
    val taskTime: String,
    val taskDate: String,
    val taskId: Long = 0L,
    val isTaskCompleted: Boolean
)

class InvalidTaskException(message: String) : Exception(message)