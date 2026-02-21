package com.homeworkapp.features.homework.domain.usecases

import com.homeworkapp.features.homework.domain.entities.Task
import com.homeworkapp.features.homework.domain.repositories.TaskRepository
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TaskRepository
) {

    suspend operator fun invoke(): Result<List<Task>> {
        return try {
            val tasks = repository.getTasks()

            val filteredTasks = tasks.filter { it.title.isNotBlank() }

            if (filteredTasks.isEmpty()) {
                Result.failure(Exception("No se encontraron tareas válidas"))
            } else {
                Result.success(filteredTasks)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

