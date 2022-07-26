package com.vvcedevelopersclub.studenttaskmanager.di

import android.content.Context
import androidx.room.Room
import com.vvcedevelopersclub.data.local.TaskManagerDatabase
import com.vvcedevelopersclub.data.local.repo.TaskRepositoryImpl
import com.vvcedevelopersclub.domain.repo.TasksRepository
import com.vvcedevelopersclub.domain.usecase.UseCaseAddTask
import com.vvcedevelopersclub.domain.usecase.UseCaseDeleteTask
import com.vvcedevelopersclub.domain.usecase.UseCaseFetchAllTasks
import com.vvcedevelopersclub.domain.usecase.UseCaseGetTaskById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class TaskManagerModule {

    @Provides
    @Singleton
    fun provideTaskManagerDatabase(@ApplicationContext context: Context) =
        Room
            .databaseBuilder(context, TaskManagerDatabase::class.java, "task_db")
            .build()

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

    @Provides
    @Singleton
    fun provideTaskRepository(
        coroutineContext: CoroutineContext,
        taskManagerDatabase: TaskManagerDatabase
    ): TasksRepository {
        return TaskRepositoryImpl(coroutineContext, taskManagerDatabase)
    }

    @Provides
    @Singleton
    fun provideUseCaseFetchAllTasks(tasksRepository: TasksRepository) =
        UseCaseFetchAllTasks(tasksRepository)

    @Provides
    @Singleton
    fun provideUseCaseAddTask(tasksRepository: TasksRepository) =
        UseCaseAddTask(tasksRepository)

    @Provides
    @Singleton
    fun provideUseCaseGetTaskById(tasksRepository: TasksRepository) =
        UseCaseGetTaskById(tasksRepository)

    @Provides
    @Singleton
    fun provideUseCaseDeleteTask(tasksRepository: TasksRepository) =
        UseCaseDeleteTask(tasksRepository)
}