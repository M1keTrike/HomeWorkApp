package com.homeworkapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.homeworkapp.core.ui.theme.HomeWorkAppTheme
import com.homeworkapp.features.homework.di.HomeworkModule
import com.homeworkapp.features.homework.presentation.screens.TaskScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = (application as HomeworkApplication).appContainer

        setContent {
            HomeWorkAppTheme {
                TaskScreen(factory = HomeworkModule(appContainer).provideTaskViewModelFactory())
            }
        }
    }
}
