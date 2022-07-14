package com.vvcedevelopersclub.studenttaskmanager.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.TaskListItemBackground

@Composable
fun TaskList(tasks: List<String>) {
    LazyColumn {
        items(tasks) {

        }
    }
}

@Preview
@Composable
fun TaskListItem() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(TaskListItemBackground)
        .padding(8.dp)
        .clickable {

        }
    ) {
        Text(
            text = "Take out the trash",
            style = MaterialTheme.typography.body1
        )
        Text(
            text = "10:30 AM",
            style = MaterialTheme.typography.body2
        )
    }
}