package com.homeworkapp.features.homework.presentation.screens

import com.homeworkapp.features.homework.domain.entities.Task

data class TaskUiState(
    val isLoading: Boolean = false,
    val tasks: List<Task> = emptyList(),
    val error: String? = null,
    val isRefreshing: Boolean = false,
    val expandedTaskIds: Set<String> = emptySet(),
    val completingTaskIds: Set<String> = emptySet(),
    val visibleTaskIds: Set<String> = emptySet()
)

