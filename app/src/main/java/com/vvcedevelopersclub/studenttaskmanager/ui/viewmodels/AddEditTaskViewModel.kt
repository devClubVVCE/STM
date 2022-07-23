package com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vvcedevelopersclub.data.local.utils.Utils
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.usecase.UseCaseAddTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val useCaseAddTask: UseCaseAddTask
) : ViewModel() {


    fun addTask() {
        viewModelScope.launch {
            useCaseAddTask.addTask(
                DMTask(
                    "Walk the dog",
                    "dog",
                    System.currentTimeMillis(),
                    Utils.getCurrentDate(),
                    0L,
                    false
                )
            )
        }
    }
}