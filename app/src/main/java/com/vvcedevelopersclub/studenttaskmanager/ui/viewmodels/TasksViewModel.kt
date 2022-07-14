package com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvcedevelopersclub.domain.usecase.UseCaseFetchAllTasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val useCaseFetchAllTasks: UseCaseFetchAllTasks
) : ViewModel() {

    var tasks = mutableStateOf<List<String>>(emptyList())

    init {
        fetchAllTasks()
    }

    private fun fetchAllTasks() {
        useCaseFetchAllTasks.fetchTasks()
    }


}