package com.pc.studyjapanesen5.presentation.alphabet

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.Constant.AlphabetType.COMBO_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.DAKUON_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.LONG_VOWEL_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.SINGLE_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.SMALL_TYPE
import com.pc.studyjapanesen5.common.extension.partitions
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.usecase.alphabet.GetAlphabetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlphabetViewModel(
    private val getAlphabetUseCase: GetAlphabetUseCase
) : BaseViewModel() {

    val alphabetSingle = SingleLiveData<List<AlphabetModel>>()
    val alphabetDakuon = SingleLiveData<List<AlphabetModel>>()
    val alphabetCombo = SingleLiveData<List<AlphabetModel>>()
    val alphabetSmall = SingleLiveData<List<AlphabetModel>>()
    val alphabetLongVowel = SingleLiveData<List<AlphabetModel>>()
    val allAlphabet = SingleLiveData<List<AlphabetModel>>()

    fun getAllJapaneseAlphabet() {
        executeTask {
            mLoading.value = true
            val allJapaneseAlphabet = withContext(Dispatchers.IO) {
                getAlphabetUseCase()
            }
            allAlphabet.value = allJapaneseAlphabet.filter {
                it.latin != null
            }.shuffled().take(4)
            val (single, dakuon, combo, small, longVowel) = allJapaneseAlphabet.partitions(
                { it.type == SINGLE_TYPE },
                { it.type == DAKUON_TYPE },
                { it.type == COMBO_TYPE },
                { it.type == SMALL_TYPE },
                { it.type == LONG_VOWEL_TYPE }
            )

            alphabetSingle.value = single
            alphabetDakuon.value = dakuon
            alphabetCombo.value = combo
            alphabetSmall.value = small
            alphabetLongVowel.value = longVowel
            mLoading.value = false
        }
    }
}
