package com.pc.studyjapanesen5.presentation.alphabet

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.Constant.COMBO_TYPE
import com.pc.studyjapanesen5.common.Constant.DAKUON_TYPE
import com.pc.studyjapanesen5.common.Constant.LONG_VOWEL_TYPE
import com.pc.studyjapanesen5.common.Constant.SINGLE_TYPE
import com.pc.studyjapanesen5.common.Constant.SMALL_TYPE
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

    fun getAllJapaneseAlphabet() {
        executeTask {
            mLoading.value = true
            val allJapaneseAlphabet = withContext(Dispatchers.IO) {
                getAlphabetUseCase()
            }
            alphabetSingle.value = allJapaneseAlphabet.filter { model ->
                model.type == SINGLE_TYPE
            }
            alphabetDakuon.value = allJapaneseAlphabet.filter { model ->
                model.type == DAKUON_TYPE
            }
            alphabetCombo.value = allJapaneseAlphabet.filter { model ->
                model.type == COMBO_TYPE
            }
            alphabetSmall.value = allJapaneseAlphabet.filter { model ->
                model.type == SMALL_TYPE
            }
            alphabetLongVowel.value = allJapaneseAlphabet.filter { model ->
                model.type == LONG_VOWEL_TYPE
            }
            mLoading.value = false
        }
    }
}
