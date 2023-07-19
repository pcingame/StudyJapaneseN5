package com.pc.studyjapanesen5.presentation.home

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetDetailVocabularyUseCase
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetVocabularyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getDetailVocabularyUseCase: GetDetailVocabularyUseCase,
    private val getVocabularyUseCase: GetVocabularyUseCase
) : BaseViewModel() {

    val groupUnit = SingleLiveData<List<Int>>()
    private val listUnit: MutableSet<Int> = mutableSetOf()
    val detailVocabulary = SingleLiveData<List<VocabularyModel>>()
    val listVocabularyGame = SingleLiveData<List<VocabularyModel>>()

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
            listVocabularyGame.value = fullVocabulary
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

    fun getVocabularyGame(unit: Int) {
        if (unit != Constant.VocabularyType.ALL_TYPE) {
            val params = GetDetailVocabularyUseCase.Input(unit)
            executeTask {
                val detail = withContext(Dispatchers.IO) {
                    getDetailVocabularyUseCase(params)
                }
                listVocabularyGame.value = detail
            }
        } else {
            executeTask {
                val fullVocabulary = withContext(Dispatchers.IO) {
                    getVocabularyUseCase()
                }
                listVocabularyGame.value = fullVocabulary
            }
        }
    }
}
