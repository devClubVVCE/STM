package com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.usecase.UseCaseFetchAllTasks
import com.vvcedevelopersclub.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val useCaseFetchAllTasks: UseCaseFetchAllTasks
) : ViewModel() {

    private val _tasksState = mutableStateOf(TasksState())
    val tasksState: State<TasksState> = _tasksState

    private var getTasksJob: Job? = null

    init {
        fetchAllTasks()
    }

    private fun fetchAllTasks() {
        getTasksJob?.cancel()
        getTasksJob = useCaseFetchAllTasks.fetchTasks().onEach { task ->
            _tasksState.value = tasksState.value.copy(tasks = task)
        }.launchIn(viewModelScope)
    }

}

data class TasksState(
    val tasks: Resource<List<DMTask>> = Resource.loading(null),
)