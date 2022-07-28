package com.devclubvvce.studenttaskmanager.ui.utils

sealed class Screen(val route: String) {
    object TasksScreen : Screen("Tasks")
    object AddEditTasksScreen : Screen("AddEditTasks")
    object InsightsScreen : Screen("InsightsScreen")
}
