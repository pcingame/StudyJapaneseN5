package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.domain.repository.AlphabetRepository
import com.pc.studyjapanesen5.data.repository.alphatbet.AlphabetRepositoryImpl
import com.pc.studyjapanesen5.domain.repository.VocabularyRepository
import com.pc.studyjapanesen5.data.repository.vocabulary.VocabularyRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<VocabularyRepository> {
        VocabularyRepositoryImpl(database = get())
    }

    single<AlphabetRepository> {
        AlphabetRepositoryImpl(database = get())
    }
}
