package com.pc.studyjapanesen5.ui.main

import androidx.lifecycle.MutableLiveData
import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.io
import com.pc.studyjapanesen5.model.entity.WordEntity
import com.pc.studyjapanesen5.repository.newword.WordRepository
import org.koin.core.component.inject

class MainViewModel : BaseViewModel() {
    private val wordRepository: WordRepository by inject()
    val listData = MutableLiveData<List<WordEntity>>()

    init {
        getAllData()
    }

    private fun getAllData() {
        executeTask {
            mLoading.value = true
            io {
                wordRepository.getAllVocabulary().collect() {
                    listData.postValue(it)
                }
            }
            mLoading.value = false
        }
    }

    fun addNewWord(wordEntity: WordEntity) {
        executeTask {
            io {
                wordRepository.addNewWord(wordEntity)
            }
        }
    }

}
