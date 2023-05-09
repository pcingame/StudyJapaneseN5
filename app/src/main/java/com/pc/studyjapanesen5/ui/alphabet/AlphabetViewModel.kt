package com.pc.studyjapanesen5.ui.alphabet

import androidx.lifecycle.MutableLiveData
import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.io
import com.pc.studyjapanesen5.model.entity.AlphabetEntity
import com.pc.studyjapanesen5.repository.alphatbet.AlphabetRepository
import org.koin.core.component.inject

class AlphabetViewModel : BaseViewModel() {
    private val alphabetRepository: AlphabetRepository by inject()
    val hiraganaSingle = MutableLiveData<List<AlphabetEntity>>()
    val hiraganaDakuon = MutableLiveData<List<AlphabetEntity>>()
    val hiraganaCombo = MutableLiveData<List<AlphabetEntity>>()
    val hiraganaSmall = MutableLiveData<List<AlphabetEntity>>()
    val hiraganaLongVowel = MutableLiveData<List<AlphabetEntity>>()

    fun getSingleHiragana(type: String) {
        executeTask {
            mLoading.value = true
            io {
                alphabetRepository.getTypeCharacter(type).collect {
                    hiraganaSingle.postValue(it)
                }
            }
            mLoading.value = false
        }
    }

    fun getDakuonHiragana(type: String) {
        executeTask {
            mLoading.value = true
            io {
                alphabetRepository.getTypeCharacter(type).collect {
                    hiraganaDakuon.postValue(it)
                }
            }
            mLoading.value = false
        }
    }

    fun getComboHiragana(type: String) {
        executeTask {
            mLoading.value = true
            io {
                alphabetRepository.getTypeCharacter(type).collect {
                    hiraganaCombo.postValue(it)
                }
            }
            mLoading.value = false
        }
    }

    fun getSmallHiragana(type: String) {
        executeTask {
            mLoading.value = true
            io {
                alphabetRepository.getTypeCharacter(type).collect {
                    hiraganaSmall.postValue(it)
                }
            }
            mLoading.value = false
        }
    }

    fun getLongVowelHiragana(type: String) {
        executeTask {
            mLoading.value = true
            io {
                alphabetRepository.getTypeCharacter(type).collect {
                    hiraganaLongVowel.postValue(it)
                }
            }
            mLoading.value = false
        }
    }
}
