package com.devclubvvce.studenttaskmanager.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.usecase.UseCaseAddTask
import com.devclubvvce.domain.usecase.UseCaseDeleteTask
import com.devclubvvce.domain.usecase.UseCaseFetchAllTasks
import com.devclubvvce.studenttaskmanager.ui.utils.TasksState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsightsViewModel @Inject constructor(
    private val useCaseFetchTasks: UseCaseFetchAllTasks,
    private val useCaseAddTask: UseCaseAddTask,
    private val useCaseDeleteTask: UseCaseDeleteTask
) : ViewModel() {
    private val _completedTasksState = mutableStateOf(TasksState())
    val completedTasksState: State<TasksState> = _completedTasksState

    private val _incompleteTasksState = mutableStateOf(TasksState())
    val incompleteTasksState: State<TasksState> = _incompleteTasksState

    private var getCompletedTasksJob: Job? = null
    private var getIncompleteTasksJob: Job? = null

    init {
        fetchCompletedTasks()
        fetchIncompleteTasks()
    }


    private fun fetchCompletedTasks(){
        getCompletedTasksJob?.cancel()
        getCompletedTasksJob = useCaseFetchTasks.fetchCompleteTasks().onEach { task ->
            _completedTasksState.value = completedTasksState.value.copy(tasks = task)
        }.launchIn(viewModelScope)
    }

    private fun fetchIncompleteTasks(){
        getIncompleteTasksJob?.cancel()
        getIncompleteTasksJob = useCaseFetchTasks.fetchIncompleteTasks().onEach { task ->
            _incompleteTasksState.value = incompleteTasksState.value.copy(tasks = task)
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