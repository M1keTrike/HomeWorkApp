package com.homeworkapp.features.homework.data.datasources.remote.model

data class WebhookResponse(
    val tareas: List<TaskDTO>
)

data class TaskDTO(
    val titulo: String,
    val subtareas: List<String>
)

