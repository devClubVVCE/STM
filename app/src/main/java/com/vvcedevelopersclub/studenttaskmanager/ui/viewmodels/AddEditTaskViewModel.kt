package com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvcedevelopersclub.data.local.utils.Utils
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.models.InvalidTaskException
import com.vvcedevelopersclub.domain.usecase.UseCaseAddTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val useCaseAddTask: UseCaseAddTask
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    fun addTask(taskName: String, taskDescription: String, taskTime: String, taskDate: String) {
        viewModelScope.launch {
            try {
                useCaseAddTask.addTask(
                    DMTask(
                       taskName = taskName,
                       taskDescription = taskDescription,
                        taskTime = taskTime,
                        taskDate = taskDate,
                        0L,
                        false
                    )
                )
                _eventFlow.emit(UiEvent.SaveTask)
            } catch (e: InvalidTaskException) {
                _eventFlow.emit(
                    UiEvent.ShowErrorMessage(
                        message = e.message ?: "Couldn't Save Task"
                    )
                )
            }
        }
    }

    sealed class UiEvent {
        data class ShowErrorMessage(val message: String) : UiEvent()
        object SaveTask : UiEvent()
    }
}