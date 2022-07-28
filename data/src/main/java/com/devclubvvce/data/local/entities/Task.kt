package com.devclubvvce.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val taskName: String,
    val taskDescription: String,
    val taskTime: String,
    val taskDate: String,
    val isTaskCompleted: Boolean,
    @PrimaryKey(autoGenerate = true)
    val taskId: Long = 0L,
)
