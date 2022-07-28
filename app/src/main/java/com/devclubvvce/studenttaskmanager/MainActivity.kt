package com.devclubvvce.studenttaskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devclubvvce.studenttaskmanager.ui.screens.AddEditTasksScreen
import com.devclubvvce.studenttaskmanager.ui.screens.InsightsScreen
import com.devclubvvce.studenttaskmanager.ui.screens.TasksScreen
import com.devclubvvce.studenttaskmanager.ui.theme.StudentTaskManagerTheme
import com.devclubvvce.studenttaskmanager.ui.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_StudentTaskManager)
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            StudentTaskManagerTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.TasksScreen.route
                ) {
                    composable(Screen.TasksScreen.route) {
                        TasksScreen(navController)
                    }
                    composable(
                        Screen.AddEditTasksScreen.route + "?taskId={taskId}",
                        arguments = listOf(
                            navArgument(name = "taskId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ) {
                        val taskId = it.arguments?.getLong("taskId") ?: -1
                        AddEditTasksScreen(
                            navController,
                            taskId
                        )
                    }
                    composable(Screen.InsightsScreen.route) {
                        InsightsScreen(navController)
                    }
                }
            }
        }
    }
}