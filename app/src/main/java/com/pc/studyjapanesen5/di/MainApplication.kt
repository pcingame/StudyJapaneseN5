package com.pc.studyjapanesen5.di

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        App.context = this
        App.application = this
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(dataModule, repositoryModule, viewModelModule)
        }
    }
}


@SuppressLint("StaticFieldLeak")
object App {
    lateinit var context: Context
    lateinit var application: Application
}
