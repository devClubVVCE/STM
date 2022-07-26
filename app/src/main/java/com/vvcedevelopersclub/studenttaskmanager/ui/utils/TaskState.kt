package com.vvcedevelopersclub.studenttaskmanager.ui.utils

import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.domain.utils.Resource

data class TasksState(
    val tasks: Resource<List<DMTask>> = Resource.loading(null),
)
