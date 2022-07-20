package com.vvcedevelopersclub.data.local.repo

import com.vvcedevelopersclub.data.local.TaskManagerDatabase
import com.vvcedevelopersclub.data.local.utils.Utils.toDMTask
import kotlinx.coroutines.flow.mapLatest
import com.vvcedevelopersclub.data.local.utils.Utils.toTask
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.repo.TasksRepository
import com.vvcedevelopersclub.domain.utils.Resource
import com.vvcedevelopersclub.domain.utils.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TaskRepositoryImpl @Inject constructor(
    private val coroutineContext: CoroutineContext,
    private val taskManagerDatabase: TaskManagerDatabase
) : TasksRepository {


    override fun fetchAllTasks(): Flow<Resource<List<DMTask>>> {
        return networkBoundResource(
            query = {
                taskManagerDatabase.taskDao().fetchTasks().mapLatest {
                    it.map { task ->
                        task.toDMTask()
                    }
                }
            },
            fetch = {
                // Retrofit query
            },
            saveFetchResult = { response ->
                // Save result to DB
            },
            shouldFetch = {
                true
            }
        )
    }

    override suspend fun addTask(dmTask: DMTask) {
        val task = dmTask.toTask()
        taskManagerDatabase.taskDao().addTask(task)
    }

    override suspend fun updateTask(dmTask: DMTask) {
    }

    override suspend fun deleteTask(dmTask: DMTask) {
    }

}