package com.vvcedevelopersclub.studenttaskmanager.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vvcedevelopersclub.domain.models.DMTask
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.TaskListItemBackground

@Composable
fun TaskList(tasks: List<DMTask>) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 20.dp)) {
        items(tasks) { item ->
            TaskListItem(item)
        }
    }
}

@Composable
fun TaskListItem(task: DMTask) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(TaskListItemBackground)
        .padding(8.dp)
        .clickable {

        }
    ) {
        Text(
            text = task.taskName,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = "${task.taskTime}",
            style = MaterialTheme.typography.body2
        )
    }
}