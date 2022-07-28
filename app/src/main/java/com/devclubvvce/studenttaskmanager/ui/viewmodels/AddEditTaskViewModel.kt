package com.devclubvvce.studenttaskmanager.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.models.InvalidTaskException
import com.devclubvvce.domain.usecase.UseCaseAddTask
import com.devclubvvce.domain.usecase.UseCaseGetTaskById
import com.devclubvvce.studenttaskmanager.ui.utils.AddEditTaskEvent
import com.devclubvvce.studenttaskmanager.ui.utils.TaskInputFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val useCaseAddTask: UseCaseAddTask,
    private val useCaseGetTaskById: UseCaseGetTaskById,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    private val _taskTitle = mutableStateOf(
        TaskInputFieldState(
            hint = "Assignment"
        )
    )
    val taskTitle: State<TaskInputFieldState> = _taskTitle

    private val _taskDescription = mutableStateOf(
        TaskInputFieldState(
            hint = "Complete assignment"
        )
    )
    val taskDescription: State<TaskInputFieldState> = _taskDescription

    private val _taskDate = mutableStateOf(
        TaskInputFieldState(
            hint = "01/01/2023"
        )
    )
    val taskDate: State<TaskInputFieldState> = _taskDate

    private val _taskTime = mutableStateOf(
        TaskInputFieldState(
            hint = "5:00 PM"
        )
    )
    val taskTime: State<TaskInputFieldState> = _taskTime

    private var currentTaskId: Long? = null

    init {
        savedStateHandle.get<Long>("taskId")?.let { taskId ->
            if (taskId != -1L) {
                viewModelScope.launch {
                    useCaseGetTaskById.getTask(taskId)?.also { task ->
                        currentTaskId = task.taskId
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.taskName,
                            isHintVisible = false
                        )
                        _taskDescription.value = taskDescription.value.copy(
                            text = task.taskDescription,
                            isHintVisible = false
                        )
                        _taskTime.value = taskTime.value.copy(
                            text = task.taskTime,
                            isHintVisible = false
                        )
                        _taskDate.value = taskDate.value.copy(
                            text = task.taskDate,
                            isHintVisible = false
                        )
                    }
                }
            }
        }
    }



    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is AddEditTaskEvent.EnteredTitle -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangedTitleFocus -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskDescription.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.EnteredDescription -> {
                _taskDescription.value = taskDescription.value.copy(
                    text = event.value
                )
            }
            is AddEditTaskEvent.ChangedDescriptionFocus -> {
                _taskDescription.value = _taskDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskDescription.value.text.isBlank()
                )
            }
            is AddEditTaskEvent.SelectDate -> {
                _taskDate.value = taskDate.value.copy(
                    text = event.value,
                    isHintVisible = event.value.isBlank()
                )
            }
            is AddEditTaskEvent.SelectTime -> {
                _taskTime.value = taskTime.value.copy(
                    text = event.value,
                    isHintVisible = event.value.isBlank()
                )
            }

            is AddEditTaskEvent.SaveTask -> {
                addTask(
                    taskTitle.value.text,
                    taskDescription.value.text,
                    taskTime.value.text,
                    taskDate.value.text,
                )
            }

        }
    }


    private fun addTask(taskName: String, taskDescription: String, taskTime: String, taskDate: String) {
        viewModelScope.launch {
            try {
                useCaseAddTask.addTask(
                    DMTask(
                        taskName = taskName,
                        taskDescription = taskDescription,
                        taskTime = taskTime,
                        taskDate = taskDate,
                        taskId = currentTaskId ?: 0L,
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