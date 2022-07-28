package com.devclubvvce.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devclubvvce.data.local.dao.TaskDao
import com.devclubvvce.data.local.entities.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)

abstract class TaskManagerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}