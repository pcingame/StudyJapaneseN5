package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.domain.usecase.alphabet.GetAlphabetUseCase
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetDetailVocabularyUseCase
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetVocabularyUseCase
import org.koin.dsl.module

val useCaseModule = module {
    //alphabet
    single { GetAlphabetUseCase() }

    //vocabulary
    single { GetVocabularyUseCase() }
    single { GetDetailVocabularyUseCase() }
}
