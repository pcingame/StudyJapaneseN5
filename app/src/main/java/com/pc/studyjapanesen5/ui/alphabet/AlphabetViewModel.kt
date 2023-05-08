package com.pc.studyjapanesen5.ui.alphabet

import androidx.lifecycle.MutableLiveData
import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.io
import com.pc.studyjapanesen5.model.entity.AlphabetEntity
import com.pc.studyjapanesen5.repository.alphatbet.AlphabetRepository
import com.pc.studyjapanesen5.repository.alphatbet.AlphabetRepositoryImpl
import org.koin.core.component.inject

class AlphabetViewModel : BaseViewModel() {
    private val alphabetRepository: AlphabetRepository by inject()
    val hiraganaSingle = MutableLiveData<List<AlphabetEntity>>()
    //private val list get() = List(30) { AlphabetEntity(1, "", "b", "あ", "") }

    //    init {
//        hiraganaSingle.value = list
//    }
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
}
