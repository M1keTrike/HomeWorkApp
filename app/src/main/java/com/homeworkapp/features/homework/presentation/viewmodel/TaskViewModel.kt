package com.homeworkapp.features.homework.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homeworkapp.features.homework.domain.usecases.GetTasksUseCase
import com.homeworkapp.features.homework.presentation.screens.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

class TaskViewModel(
    private val getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            getTasksUseCase().fold(
                onSuccess = { tasks ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        tasks = tasks,
                        visibleTaskIds = tasks.map { it.id }.toSet(),
                        error = null
                    )
                },
                onFailure = { _ ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = "Error al cargar las tareas"
                    )
                }
            )
        }
    }

    fun toggleTaskExpanded(taskId: String) {
        val currentExpanded = _uiState.value.expandedTaskIds
        _uiState.value = _uiState.value.copy(
            expandedTaskIds = if (taskId in currentExpanded) {
                currentExpanded - taskId
            } else {
                currentExpanded + taskId
            }
        )
    }

    fun toggleSubtaskCompleted(taskId: String, subtaskIndex: Int) {
        val updatedTasks = _uiState.value.tasks.map { task ->
            if (task.id == taskId) {
                val updatedSubtasks = task.subtasks.mapIndexed { index, subtask ->
                    if (index == subtaskIndex) {
                        subtask.copy(isCompleted = !subtask.isCompleted)
                    } else {
                        subtask
                    }
                }
                task.copy(subtasks = updatedSubtasks)
            } else {
                task
            }
        }

        _uiState.value = _uiState.value.copy(tasks = updatedTasks)

        val task = updatedTasks.find { it.id == taskId }
        if (task?.subtasks?.all { it.isCompleted } == true) {
            markTaskAsCompleting(taskId)
        }
    }

    private fun markTaskAsCompleting(taskId: String) {
        _uiState.value = _uiState.value.copy(
            completingTaskIds = _uiState.value.completingTaskIds + taskId,
            visibleTaskIds = _uiState.value.visibleTaskIds - taskId
        )

        viewModelScope.launch {
            delay(500)
            removeTask(taskId)
        }
    }

    private fun removeTask(taskId: String) {
        _uiState.value = _uiState.value.copy(
            tasks = _uiState.value.tasks.filter { it.id != taskId },
            completingTaskIds = _uiState.value.completingTaskIds - taskId,
            expandedTaskIds = _uiState.value.expandedTaskIds - taskId,
            visibleTaskIds = _uiState.value.visibleTaskIds - taskId
        )
    }
}

