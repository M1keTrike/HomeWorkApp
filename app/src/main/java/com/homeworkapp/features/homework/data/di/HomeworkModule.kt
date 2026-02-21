package com.homeworkapp.features.homework.data.di

import com.homeworkapp.features.homework.data.repositories.TaskRepositoryImpl
import com.homeworkapp.features.homework.domain.repositories.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeworkModule {

    @Binds
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}
