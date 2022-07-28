package com.devclubvvce.studenttaskmanager.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.devclubvvce.domain.models.DMTask
import com.devclubvvce.studenttaskmanager.ui.theme.DeleteBackground
import com.devclubvvce.studenttaskmanager.ui.theme.TaskListItemBackground
import com.devclubvvce.studenttaskmanager.ui.theme.UpdateBackground
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

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
