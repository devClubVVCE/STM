package com.devclubvvce.studenttaskmanager.ui.utils

import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.utils.Resource

data class TasksState(
    val tasks: Resource<List<DMTask>> = Resource.loading(null),
)
