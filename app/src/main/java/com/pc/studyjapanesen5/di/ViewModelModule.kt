package com.pc.studyjapanesen5.di

import com.pc.studyjapanesen5.presentation.alphabet.AlphabetViewModel
import com.pc.studyjapanesen5.presentation.home.HomeViewModel
import com.pc.studyjapanesen5.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { AlphabetViewModel() }
    viewModel { HomeViewModel() }
}
