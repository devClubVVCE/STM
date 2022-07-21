package com.vvcedevelopersclub.domain.models

import java.util.*

data class DMTask(
    val taskName: String,
    val taskDescription: String,
    val taskTime: Long,
    val taskDate: String,
    val taskId: Long = 0L,
    val isTaskCompleted: Boolean
)