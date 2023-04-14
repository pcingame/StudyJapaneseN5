package com.pc.studyjapanesen5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.io
import com.pc.studyjapanesen5.model.WordEntity
import com.pc.studyjapanesen5.repository.WordRepository
import kotlinx.coroutines.launch
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
