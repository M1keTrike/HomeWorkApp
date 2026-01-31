package com.homeworkapp.features.homework.di

import com.homeworkapp.core.di.AppContainer
import com.homeworkapp.features.homework.domain.usecases.GetTasksUseCase
import com.homeworkapp.features.homework.presentation.viewmodel.TaskViewModelFactory

class HomeworkModule(
    private val appContainer: AppContainer
) {

    private fun provideGetTasksUseCase(): GetTasksUseCase {
        return GetTasksUseCase(appContainer.taskRepository)
    }

    fun provideTaskViewModelFactory(): TaskViewModelFactory {
        return TaskViewModelFactory(
            getTasksUseCase = provideGetTasksUseCase()
        )
    }
}
