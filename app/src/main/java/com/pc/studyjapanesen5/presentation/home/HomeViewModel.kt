package com.pc.studyjapanesen5.presentation.home

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetVocabularyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    private val getVocabularyUseCase: GetVocabularyUseCase by inject()

    val itemVocabulary = SingleLiveData<MutableMap<Int, List<VocabularyModel>>>()
    val groupUnit = SingleLiveData<List<Int>>()
    private val listUnit: MutableList<Int> = mutableListOf()

    fun getAllVocabulary() {
        executeTask {
            val fullVocabulary = withContext(Dispatchers.IO) {
                getVocabularyUseCase()
            }
            val vocabularyMap = fullVocabulary.groupBy { vocabularyModel ->
                vocabularyModel.unit
            }
            itemVocabulary.value = vocabularyMap.toMutableMap()
            vocabularyMap.forEach {
                listUnit.add(it.key)
            }
            groupUnit.value = listUnit
        }
    }
}
