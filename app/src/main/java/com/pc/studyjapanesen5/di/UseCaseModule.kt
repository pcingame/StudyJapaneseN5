package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.domain.usecase.alphabet.GetAlphabetUseCase
import org.koin.dsl.module

val useCaseModule = module {
    //alphabet
    single { GetAlphabetUseCase() }
}
