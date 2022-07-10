package com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class TasksViewModel : ViewModel() {
    var tasks = mutableStateOf<List<String>>(emptyList())
}