package com.devclubvvce.data.local.repo

import com.devclubvvce.data.local.TaskManagerDatabase
import com.devclubvvce.data.local.utils.Utils.toDMTask
import kotlinx.coroutines.flow.mapLatest
import com.devclubvvce.data.local.utils.Utils.toTask
import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.repo.TasksRepository
import com.devclubvvce.domain.utils.Resource
import com.devclubvvce.domain.utils.networkBoundResource
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
            saveFetchResult = { _ ->
                // Save result to DB
            },
            shouldFetch = {
                true
            }
        )
    }

    override fun fetchTaskDates(): Flow<Resource<List<String>>> {
        return networkBoundResource(
            query = {
                taskManagerDatabase.taskDao().fetchTaskDates()
            },
            fetch = {
                // Retrofit query
            },
            saveFetchResult = { _ ->
                // Save result to DB
            },
            shouldFetch = {
                true
            }
        )
    }

    override suspend fun fetchTaskById(taskId: Long): DMTask? {
        return taskManagerDatabase.taskDao().getTaskById(taskId)?.toDMTask()
    }

    override suspend fun addTask(dmTask: DMTask) {
        taskManagerDatabase.taskDao().addTask(dmTask.toTask())
    }

    override suspend fun deleteTask(dmTask: DMTask) {
        taskManagerDatabase.taskDao().deleteTask(dmTask.toTask())
    }

    override fun fetchCompletedTasks(): Flow<Resource<List<DMTask>>> {
        return networkBoundResource(
            query = {
                taskManagerDatabase.taskDao().fetchCompletedTasks().mapLatest {
                    it.map { task ->
                        task.toDMTask()
                    }
                }
            },
            fetch = {
                // Retrofit query
            },
            saveFetchResult = { _ ->
                // Save result to DB
            },
            shouldFetch = {
                true
            }
        )
    }

    override fun fetchIncompleteTasks(): Flow<Resource<List<DMTask>>> {
        return networkBoundResource(
            query = {
                taskManagerDatabase.taskDao().fetchIncompleteTasks().mapLatest {
                    it.map { task ->
                        task.toDMTask()
                    }
                }
            },
            fetch = {
                // Retrofit query
            },
            saveFetchResult = { _ ->
                // Save result to DB
            },
            shouldFetch = {
                true
            }
        )
    }

}