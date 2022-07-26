package com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.usecase.UseCaseAddTask
import com.vvcedevelopersclub.domain.usecase.UseCaseDeleteTask
import com.vvcedevelopersclub.domain.usecase.UseCaseFetchAllTasks
import com.vvcedevelopersclub.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val useCaseFetchAllTasks: UseCaseFetchAllTasks,
    private val useCaseAddTask: UseCaseAddTask,
    private val useCaseDeleteTask: UseCaseDeleteTask
) : ViewModel() {

    private val _tasksState = mutableStateOf(TasksState())
    val tasksState: State<TasksState> = _tasksState

    private val _taskDates = mutableStateOf(TasksDatesState())
    val taskDates: State<TasksDatesState> = _taskDates

    private var getTasksJob: Job? = null
    private var getTaskDatesJob: Job? = null

    init {
        fetchTaskDates()
        fetchAllTasks()
    }

    private fun fetchAllTasks() {
        getTasksJob?.cancel()
        getTasksJob = useCaseFetchAllTasks.fetchTasks().onEach { task ->
            _tasksState.value = tasksState.value.copy(tasks = task)
        }.launchIn(viewModelScope)

    }

    private fun fetchTaskDates() {
        getTaskDatesJob?.cancel()
        getTaskDatesJob = useCaseFetchAllTasks.fetchTaskDates().onEach { task ->
            _taskDates.value = taskDates.value.copy(taskDates = task)
        }.launchIn(viewModelScope)

    }

    fun deleteTask(dmTask: DMTask) {
        viewModelScope.launch {
            useCaseDeleteTask.deleteTask(dmTask)
        }
    }

    fun updateTask(dmTask: DMTask) {
        viewModelScope.launch {
            useCaseAddTask.addTask(dmTask.copy(isTaskCompleted = !dmTask.isTaskCompleted))
        }
    }

}

data class TasksState(
    val tasks: Resource<List<DMTask>> = Resource.loading(null),
)

data class TasksDatesState(
    val taskDates: Resource<List<String>> = Resource.loading(null),
)