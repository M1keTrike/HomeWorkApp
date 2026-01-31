package com.homeworkapp.features.homework.data.datasources.remote.mapper

import com.homeworkapp.features.homework.data.datasources.remote.model.TaskDTO
import com.homeworkapp.features.homework.data.datasources.remote.model.WebhookResponse
import com.homeworkapp.features.homework.domain.entities.Task
import com.homeworkapp.features.homework.domain.entities.Subtask

fun TaskDTO.toDomain(): Task {
    return Task(
        title = titulo,
        subtasks = subtareas.map { subtaskTitle ->
            Subtask(
                title = subtaskTitle,
                isCompleted = false
            )
        }
    )
}

fun WebhookResponse.toDomain(): List<Task> {
    return tareas.map { it.toDomain() }
}
