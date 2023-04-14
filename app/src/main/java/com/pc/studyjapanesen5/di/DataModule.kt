package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.model.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        AppDatabase.build(androidContext())
    }
}
