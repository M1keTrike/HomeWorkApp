package com.homeworkapp.core.di

import android.content.Context
import com.homeworkapp.core.network.HomeworkApi
import com.homeworkapp.features.homework.data.repositories.TaskRepositoryImpl
import com.homeworkapp.features.homework.domain.repositories.TaskRepository
import com.homeworkapp.features.homework.domain.usecases.GetTasksUseCase
import com.homeworkapp.features.homework.presentation.viewmodel.TaskViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppContainer(context: Context) {


    private val _baseUrl = "https://n8n.alphahills.site/"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(_baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val homeworkApi: HomeworkApi by lazy {
        retrofit.create(HomeworkApi::class.java)
    }

    val taskRepository: TaskRepository by lazy {
        TaskRepositoryImpl(homeworkApi)
    }

}

