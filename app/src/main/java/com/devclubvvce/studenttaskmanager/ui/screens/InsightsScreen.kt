package com.devclubvvce.studenttaskmanager.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.domain.utils.Status
import com.devclubvvce.studenttaskmanager.R
import com.devclubvvce.studenttaskmanager.ui.screens.components.CircularProgressIndicatorView
import com.devclubvvce.studenttaskmanager.ui.screens.components.PieChartSection
import com.devclubvvce.studenttaskmanager.ui.screens.components.TaskListItem
import com.devclubvvce.studenttaskmanager.ui.theme.*
import com.devclubvvce.studenttaskmanager.ui.utils.Screen
import com.devclubvvce.studenttaskmanager.ui.viewmodels.InsightsViewModel

@Composable
fun InsightsScreen(navController: NavController, viewModel: InsightsViewModel = hiltViewModel()) {


    val completedTasks = viewModel.completedTasksState.value
    val incompleteTasks = viewModel.incompleteTasksState.value

    if (completedTasks.tasks.status == Status.LOADING || incompleteTasks.tasks.status == Status.LOADING) {
        CircularProgressIndicatorView()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HeadingSection()
            PieChartSection(
                points = listOf(
                    completedTasks.tasks.data!!.size.toFloat(),
                    incompleteTasks.tasks.data!!.size.toFloat()
                ), colors = listOf(
                    DoneTitleHeaderColor,
                    DueTitleHeaderColor
                )
            )
            TasksSection(
                completedTasks = completedTasks.tasks.data!!,
                incompleteTasks = incompleteTasks.tasks.data!!,
                onDeleteAction = {
                    viewModel.deleteTask(it)
                },
                onUpdateAction = { viewModel.updateTask(it) },
                onTaskItemCLicked = { taskId ->
                    navController.navigate(Screen.AddEditTasksScreen.route + "?taskId=$taskId")
                }
            )
        }
    }
}

@Composable
fun HeadingSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.insights),
            style = MaterialTheme.typography.h2
        )
        Text(
            text = stringResource(id = R.string.insights_subtitle),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

fun DrawScope.drawArc(
    color: Color, startAngle: Float,
    sweepAngle: Float
) {
    val sizeArc = size / 2.25F
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = true,
        topLeft = Offset(
            (size.width - size.width * 0.65f) / 2f,
            (size.width * 0.65f - sizeArc.width) / 2f
        ),
        size = Size(width = size.width * 0.65f, height = size.width * 0.65f)
    )
}

@Composable
fun TasksSection(
    completedTasks: List<DMTask>,
    incompleteTasks: List<DMTask>,
    onDeleteAction: (dmTask: DMTask) -> Unit,
    onUpdateAction: (dmTask: DMTask) -> Unit,
    onTaskItemCLicked: (taskId: Long) -> Unit,
) {

    val completedTasksVisible = remember {
        mutableStateOf(false)
    }

    val incompleteTasksVisible = remember {
        mutableStateOf(false)
    }

    LazyColumn {
        item {
            ListHeader(
                title = R.string.due,
                color = DueTitleHeaderColor,
                onMinimizeList = {
                    incompleteTasksVisible.value = !incompleteTasksVisible.value
                }, incompleteTasksVisible.value
            )
        }
        items(incompleteTasks) { task ->
            AnimatedVisibility(
                visible = incompleteTasksVisible.value,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TaskListItem(
                    task = task,
                    onTaskItemClicked = {
                        onTaskItemCLicked(it)
                    },
                    onDeleteAction = {
                        onDeleteAction(it)
                    },
                    onUpdateAction = {
                        onUpdateAction(it)
                    })

            }
            if (incompleteTasksVisible.value) {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        item {
            ListHeader(
                title = R.string.done,
                color = DoneTitleHeaderColor,
                onMinimizeList = {
                    completedTasksVisible.value = !completedTasksVisible.value
                },
                completedTasksVisible.value
            )
        }
        items(completedTasks) { task ->
            AnimatedVisibility(
                visible = completedTasksVisible.value,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                TaskListItem(
                    task = task,
                    onTaskItemClicked = {
                        onTaskItemCLicked(it)
                    },
                    onDeleteAction = {
                        onDeleteAction(it)
                    },
                    onUpdateAction = {
                        onUpdateAction(it)
                    })
            }
            if (completedTasksVisible.value) {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ListHeader(title: Int, color: Color, onMinimizeList: () -> Unit, isVisible: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.h2.copy(color = color),
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Icon(
            if (isVisible) {
                Icons.Filled.Minimize
            } else {
                Icons.Filled.Add
            },
            contentDescription = "Minimize list",
            modifier = Modifier.clickable {
                onMinimizeList()
            },
            tint = color
        )
    }
}