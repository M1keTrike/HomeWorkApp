package com.homeworkapp.features.homework.domain.entities

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val subtasks: List<Subtask>
)
