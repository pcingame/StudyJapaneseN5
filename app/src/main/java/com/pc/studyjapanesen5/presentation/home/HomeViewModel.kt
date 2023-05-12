package com.pc.studyjapanesen5.presentation.home

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetDetailVocabularyUseCase
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetVocabularyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    private val getVocabularyUseCase: GetVocabularyUseCase by inject()
    private val getDetailVocabularyUseCase: GetDetailVocabularyUseCase by inject()

    val groupUnit = SingleLiveData<List<Int>>()
    private val listUnit: MutableSet<Int> = mutableSetOf()
    val detailVocabulary = SingleLiveData<List<VocabularyModel>>()

    fun getAllVocabulary() {
        executeTask {
            val fullVocabulary = withContext(Dispatchers.IO) {
                getVocabularyUseCase()
            }
            fullVocabulary.groupBy { vocabularyModel ->
                vocabularyModel.unit
            }.map {
                listUnit.add(it.key)
            }

            groupUnit.value = listUnit.toList()
        }
    }

    fun getVocabularyByUnit(unit: Int) {
        val params = GetDetailVocabularyUseCase.Input(unit)
        executeTask {
            val detail = withContext(Dispatchers.IO) {
                getDetailVocabularyUseCase(params)
            }
            detailVocabulary.value = detail
        }
    }
}
