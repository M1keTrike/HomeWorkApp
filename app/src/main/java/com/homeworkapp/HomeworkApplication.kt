package com.homeworkapp

import android.app.Application
import com.homeworkapp.core.di.AppContainer

class HomeworkApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}

