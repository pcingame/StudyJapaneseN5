package com.pc.studyjapanesen5.presentation.alphabet

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.usecase.alphabet.GetAlphabetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.inject

class AlphabetViewModel : BaseViewModel() {

    private val getAlphabetUseCase: GetAlphabetUseCase by inject()

    val alphabetSingle = SingleLiveData<List<AlphabetModel>>()
    val alphabetDakuon = SingleLiveData<List<AlphabetModel>>()
    val alphabetCombo = SingleLiveData<List<AlphabetModel>>()
    val alphabetSmall = SingleLiveData<List<AlphabetModel>>()
    val alphabetLongVowel = SingleLiveData<List<AlphabetModel>>()


    fun getSingle(type: String) {
        val params = GetAlphabetUseCase.Input(type)
        executeTask {
            mLoading.value = true
            val single = withContext(Dispatchers.IO) {
                getAlphabetUseCase(params)
            }
            alphabetSingle.value = single
            mLoading.value = false
        }
    }

    fun getDakuon(type: String) {
        val params = GetAlphabetUseCase.Input(type)
        executeTask {
            mLoading.value = true
            val dakuon = withContext(Dispatchers.IO) {
                getAlphabetUseCase(params)
            }
            alphabetDakuon.value = dakuon
            mLoading.value = false
        }
    }

    fun getCombo(type: String) {
        val params = GetAlphabetUseCase.Input(type)
        executeTask {
            mLoading.value = true
            val combo = withContext(Dispatchers.IO) {
                getAlphabetUseCase(params)
            }
            alphabetCombo.value = combo
            mLoading.value = false
        }
    }

    fun getSmall(type: String) {
        val params = GetAlphabetUseCase.Input(type)
        executeTask {
            mLoading.value = true
            val small = withContext(Dispatchers.IO) {
                getAlphabetUseCase(params)
            }
            alphabetSmall.value = small
            mLoading.value = false
        }
    }

    fun getLongVowel(type: String) {
        val params = GetAlphabetUseCase.Input(type)
        executeTask {
            mLoading.value = true
            val longVowel = withContext(Dispatchers.IO) {
                getAlphabetUseCase(params)
            }
            alphabetLongVowel.value = longVowel
            mLoading.value = false
        }
    }
}
