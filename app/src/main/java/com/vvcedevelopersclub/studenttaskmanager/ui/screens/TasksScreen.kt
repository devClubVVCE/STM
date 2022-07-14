package com.vvcedevelopersclub.studenttaskmanager.ui.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.components.EmptyListView
import com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels.TasksViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = viewModel()
) = if (viewModel.tasks.value.isEmpty()) {
    EmptyListView()
} else {
    Text("Tasks present")
}