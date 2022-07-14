package com.vvcedevelopersclub.data.local.repo

import com.vvcedevelopersclub.data.local.TaskManagerDatabase
import com.vvcedevelopersclub.data.local.utils.Utils.toDMTask
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.repo.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TaskRepositoryImpl @Inject constructor(
    private val coroutineContext: CoroutineContext,
    private val taskManagerDatabase: TaskManagerDatabase
) : TasksRepository {


    override fun fetchAllTasks(): Flow<List<DMTask>> {
        val tasks = taskManagerDatabase.taskDao().fetchTasks()
        return tasks.mapLatest {
            it.map { task ->
                task.toDMTask()
            }
        }
    }
}