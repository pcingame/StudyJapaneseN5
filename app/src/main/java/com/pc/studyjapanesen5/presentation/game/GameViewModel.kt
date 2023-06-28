package com.pc.studyjapanesen5.presentation.game

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.usecase.alphabet.GetAlphabetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.inject

class GameViewModel : BaseViewModel() {
    private val getAlphabetUseCase: GetAlphabetUseCase by inject()

    val hiraganaAlphabet = SingleLiveData<List<AlphabetModel>>()
    val katakanaAlphabet = SingleLiveData<List<AlphabetModel>>()

    fun getAlphabet() {
        executeTask {
            mLoading.value = true
            val allAlphabet = withContext(Dispatchers.IO) {
                getAlphabetUseCase()
            }
            val hiragana = allAlphabet.filter {
                it.hiragana != null
            }
            val katakana = allAlphabet.filter {
                it.katakana != null
            }
            hiraganaAlphabet.value = hiragana
            katakanaAlphabet.value = katakana
            mLoading.value = false
        }
    }
}
