package com.vvcedevelopersclub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vvcedevelopersclub.data.local.dao.TaskDao
import com.vvcedevelopersclub.data.local.entities.Task
import com.vvcedevelopersclub.data.local.utils.DateConverter

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class TaskManagerDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}