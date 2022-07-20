package com.vvcedevelopersclub.studenttaskmanager.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vvcedevelopersclub.domain.utils.Status
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.components.CircularProgressIndicatorView
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.components.EmptyListView
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.components.TaskList
import com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels.TasksViewModel

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val tasksState = viewModel.tasksState.value

    when (tasksState.tasks.status) {
        Status.LOADING -> {
            CircularProgressIndicatorView()
        }
        Status.SUCCESS -> {
            if (tasksState.tasks.data != null && tasksState.tasks.data?.isEmpty() == true) {
                EmptyListView(navController) {
                }
            } else if (tasksState.tasks.data != null && tasksState.tasks.data?.isEmpty() != true) {
                TaskList(tasksState.tasks.data!!)
            }
        }
        else -> {
            EmptyListView(navController) {
            }
        }
    }
}