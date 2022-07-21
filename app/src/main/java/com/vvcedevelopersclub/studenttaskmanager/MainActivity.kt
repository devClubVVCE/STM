package com.vvcedevelopersclub.studenttaskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.AddEditTasksScreen
import com.vvcedevelopersclub.studenttaskmanager.ui.screens.TasksScreen
import com.vvcedevelopersclub.studenttaskmanager.ui.theme.StudentTaskManagerTheme
import com.vvcedevelopersclub.studenttaskmanager.ui.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        val splashScreen = installSplashScreen()

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
                    composable(Screen.AddEditTasksScreen.route) {
                        AddEditTasksScreen(navController)
                    }
                }
            }
        }
    }
}