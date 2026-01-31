package com.homeworkapp.features.homework.domain.repositories

import com.homeworkapp.features.homework.domain.entities.Task

interface TaskRepository {
    suspend fun getTasks(): List<Task>
}

