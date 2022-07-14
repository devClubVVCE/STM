package com.vvcedevelopersclub.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Task(
    val taskName: String,
    val taskDescription: String,
    val taskTime: Long,
    val taskDate: Date,
    val isTaskCompleted: Boolean,
    @PrimaryKey(autoGenerate = true)
    val taskId: Long = 0L,
)
