package com.vvcedevelopersclub.studenttaskmanager.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.vvcedevelopersclub.data.local.utils.Utils
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.studenttaskmanager.R
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.*
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun TaskList(
    tasks: List<DMTask>,
    onAddTaskButtonClicked: () -> Unit,
    onTaskItemClicked: (taskId: Long) -> Unit,
    onInsightsTaskButtonClicked: () -> Unit,
    taskDates: List<String>,
    onDeleteAction: (task: DMTask) -> Unit,
    onUpdateAction: (task: DMTask) -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBackground)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.heading),
            style = MaterialTheme.typography.h2
        )
        Text(
            text = LocalContext.current.resources.getQuantityString(
                R.plurals.tasks_subheading,
                tasks.filter { !it.isTaskCompleted }.size,
                tasks.filter { !it.isTaskCompleted }.size
            ),
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(vertical = 20.dp)
        ) {

            items(taskDates) { date ->
                Text(checkDate(date), style = MaterialTheme.typography.body2)
                Spacer(modifier = Modifier.height(16.dp))
                for (task in tasks) {
                    if (task.taskDate == date) {
                        TaskListItem(task, onTaskItemClicked, onDeleteAction, onUpdateAction)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
        TaskActions(onAddTaskButtonClicked, onInsightsTaskButtonClicked)

    }
}

@Composable
fun TaskListItem(
    task: DMTask,
    onTaskItemClicked: (taskId: Long) -> Unit,
    onDeleteAction: (task: DMTask) -> Unit,
    onUpdateAction: (task: DMTask) -> Unit,
) {


    val update = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        background = UpdateBackground,
        onSwipe = {
            onUpdateAction(task)
        }
    )

    val delete = SwipeAction(
        icon = {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
        },
        background = DeleteBackground,
        onSwipe = {
            onDeleteAction(task)
        },
    )

    SwipeableActionsBox(
        startActions = listOf(update),
        endActions = listOf(delete),
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(TaskListItemBackground)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onTaskItemClicked(task.taskId)
            }
            .background(TaskListItemBackground)
            .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(
                text = task.taskName,
                style = if (task.isTaskCompleted) {
                    MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.LineThrough)
                } else {
                    MaterialTheme.typography.body1
                },
            )
            Text(
                text = task.taskTime,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun TaskActions(onAddTaskButtonClicked: () -> Unit, onInsightsTaskButtonClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonComponent(
            PrimaryButtonBackground,
            stringResource(id = R.string.add_tasks),
            onClick = onAddTaskButtonClicked
        )
        ButtonComponent(
            SecondaryButtonBackground,
            stringResource(id = R.string.insights),
            icon = Icons.Outlined.Visibility,
            onClick = onInsightsTaskButtonClicked
        )
    }
}

private fun checkDate(date: String) = when (date) {
    Utils.getCurrentDate() -> {
        "Today"
    }
    Utils.getPreviousDay() -> {
        "Yesterday"
    }
    Utils.getNextDay() -> {
        "Tomorrow"
    }
    else -> {
        date
    }
}