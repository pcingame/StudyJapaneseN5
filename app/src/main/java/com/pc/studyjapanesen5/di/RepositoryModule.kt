package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.repository.WordRepository
import com.pc.studyjapanesen5.repository.WordRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WordRepository> {
        WordRepositoryImpl(database = get())
    }
}
