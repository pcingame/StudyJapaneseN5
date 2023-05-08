package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.repository.alphatbet.AlphabetRepository
import com.pc.studyjapanesen5.repository.alphatbet.AlphabetRepositoryImpl
import com.pc.studyjapanesen5.repository.newword.WordRepository
import com.pc.studyjapanesen5.repository.newword.WordRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WordRepository> {
        WordRepositoryImpl(database = get())
    }

    single<AlphabetRepository> {
        AlphabetRepositoryImpl(database = get())
    }
}
