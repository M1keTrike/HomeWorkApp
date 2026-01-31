package com.homeworkapp.features.homework.data.repositories

import com.homeworkapp.core.network.HomeworkApi
import com.homeworkapp.features.homework.data.datasources.remote.mapper.toDomain
import com.homeworkapp.features.homework.domain.entities.Task
import com.homeworkapp.features.homework.domain.repositories.TaskRepository

class TaskRepositoryImpl(
    private val api: HomeworkApi
) : TaskRepository {

    override suspend fun getTasks(): List<Task> {
        val response = api.getWebhookTasks()
        return response.toDomain()
    }
}

