package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.domain.repository.AlphabetRepository
import com.pc.studyjapanesen5.data.repository.alphatbet.AlphabetRepositoryImpl
import com.pc.studyjapanesen5.domain.repository.WordRepository
import com.pc.studyjapanesen5.data.repository.newword.WordRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<WordRepository> {
        WordRepositoryImpl(database = get())
    }

    single<AlphabetRepository> {
        AlphabetRepositoryImpl(database = get())
    }
}
