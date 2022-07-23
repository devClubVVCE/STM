package com.vvcedevelopersclub.studenttaskmanager.ui.utils

sealed class Screen(val route:String){
    object TasksScreen:Screen("Tasks")
    object AddEditTasksScreen:Screen("AddEditTasks")
}
