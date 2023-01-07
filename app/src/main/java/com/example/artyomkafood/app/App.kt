package com.example.artyomkafood.app

import android.app.Application
import com.example.artyomkafood.di.dataBaseModule
import com.example.artyomkafood.di.repositoryModule
import com.example.artyomkafood.di.viewModelModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin() {
            androidContext(this@App)
            modules(
                listOf(
                    dataBaseModule, repositoryModule, viewModelModel
                )
            )
        }
    }
}