package com.vvcedevelopersclub.studenttaskmanager.ui.screens

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.components.EmptyListView
import com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels.TasksViewModel

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) = if (viewModel.tasks.value.isEmpty()) {
    EmptyListView()
} else {
    Text("Tasks present")
}