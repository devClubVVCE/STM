package com.vvcedevelopersclub.studenttaskmanager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.Background
import com.vvcedevelopersclub.studenttaskmanager.ui.viewmodels.AddEditTaskViewModel

@Composable
fun AddEditTasksScreen(navController: NavController,viewModel:AddEditTaskViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Button(onClick = { viewModel.addTask() }) {
            Text(text = "Add Tasks")
        }
    }
}