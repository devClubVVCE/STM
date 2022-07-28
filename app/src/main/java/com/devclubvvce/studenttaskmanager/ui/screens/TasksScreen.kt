package com.devclubvvce.studenttaskmanager.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devclubvvce.domain.utils.Status
import com.devclubvvce.studenttaskmanager.ui.screens.components.CircularProgressIndicatorView
import com.devclubvvce.studenttaskmanager.ui.screens.components.EmptyListView
import com.devclubvvce.studenttaskmanager.ui.screens.components.TaskList
import com.devclubvvce.studenttaskmanager.ui.utils.Screen
import com.devclubvvce.studenttaskmanager.ui.viewmodels.TasksViewModel

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val tasksState = viewModel.tasksState.value
    val taskDatesState = viewModel.taskDates.value

    if (tasksState.tasks.status == Status.LOADING || taskDatesState.taskDates.status == Status.LOADING) {
        CircularProgressIndicatorView()
    } else {
        if (tasksState.tasks.data != null && tasksState.tasks.data?.isEmpty() == true) {
            EmptyListView {
                navController.navigate(Screen.AddEditTasksScreen.route)
            }
        } else if (
            tasksState.tasks.data != null
            && tasksState.tasks.data?.isEmpty() != true
            && taskDatesState.taskDates.data != null
            && taskDatesState.taskDates.data?.isEmpty() != true) {
            TaskList(
                tasks = tasksState.tasks.data!!,
                onAddTaskButtonClicked = {
                    navController.navigate(Screen.AddEditTasksScreen.route)
                },
                onInsightsTaskButtonClicked = {
                    navController.navigate(Screen.InsightsScreen.route)
                },
                onTaskItemClicked = { taskId ->
                    navController.navigate(Screen.AddEditTasksScreen.route+"?taskId=$taskId")
                },
                taskDates = taskDatesState.taskDates.data!!,
                onDeleteAction = {
                    viewModel.deleteTask(it)
                },
                onUpdateAction = {
                    viewModel.updateTask(it)
                }
            )
        }
    }
}
