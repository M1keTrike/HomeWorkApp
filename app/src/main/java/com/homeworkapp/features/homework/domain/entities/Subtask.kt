package com.homeworkapp.features.homework.domain.entities

import java.util.UUID

data class Subtask(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val isCompleted: Boolean = false
)
