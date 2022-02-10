package com.airongomes.eventsapi

import android.app.Application
import com.airongomes.eventsapi.di.eventModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(eventModule)
            )
        }
    }
}