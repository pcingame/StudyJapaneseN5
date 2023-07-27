package com.pc.studyjapanesen5.presentation.home

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.model.VocabularyQuestionModel
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
    val listVocabularyGame = SingleLiveData<List<VocabularyQuestionModel>>()

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

    fun getVocabularyGame(unit: Int) {
        val vocabularyQuestionData = mutableListOf<VocabularyQuestionModel>()
        if (unit != Constant.VocabularyType.ALL_TYPE) {
            val params = GetDetailVocabularyUseCase.Input(unit)
            executeTask {
                val detail = withContext(Dispatchers.IO) {
                    getDetailVocabularyUseCase(params)
                }.toMutableList()
                while (vocabularyQuestionData.size < Constant.AlphabetType.NUMBER_OF_QUESTION) {
                    val listShuffle = detail.shuffled().take(4)
                    val listAnswer = listShuffle.shuffled()
                    val questionVocabulary = listShuffle.first().newWord
                    val questionKanji = listShuffle.first().kanji
                    val correctAnswer = listShuffle.first().wordMeaning

                    vocabularyQuestionData.add(
                        VocabularyQuestionModel(
                            questionVocabulary,
                            questionKanji,
                            correctAnswer,
                            listAnswer[0].wordMeaning,
                            listAnswer[1].wordMeaning,
                            listAnswer[2].wordMeaning,
                            listAnswer[2].wordMeaning
                        )
                    )

                    detail.removeIf {
                        it.newWord == questionVocabulary
                    }
                }

                listVocabularyGame.value = vocabularyQuestionData
            }
        } else {
//            executeTask {
//                val fullVocabulary = withContext(Dispatchers.IO) {
//                    getVocabularyUseCase()
//                }
//                listVocabularyGame.value = fullVocabulary
//            }
        }
    }
}
