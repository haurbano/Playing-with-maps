package com.example.googlemapstest

import android.app.Application
import com.example.googlemapstest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MapApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MapApp)
            modules(listOf(appModule))
        }
    }
}