package com.pc.studyjapanesen5.presentation.game

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.Constant.AlphabetType.HIRAGANA_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.KATAKANA_TYPE
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.model.AlphabetQuestionModel
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.model.VocabularyQuestionModel
import com.pc.studyjapanesen5.domain.usecase.alphabet.GetAlphabetUseCase
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetDetailVocabularyUseCase
import com.pc.studyjapanesen5.domain.usecase.vocabulary.GetVocabularyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameViewModel(
    private val getAlphabetUseCase: GetAlphabetUseCase,
    private val getDetailVocabularyUseCase: GetDetailVocabularyUseCase,
    private val getVocabularyUseCase: GetVocabularyUseCase
) : BaseViewModel() {

    val listAlphabetQuestionModel = SingleLiveData<List<AlphabetQuestionModel>>()
    val listVocabularyGame = SingleLiveData<List<VocabularyQuestionModel>>()

    fun getAlphabetGameData(typeQuestion: String) {
        executeTask {
            mLoading.value = true
            val allJapaneseAlphabet = withContext(Dispatchers.IO) {
                getAlphabetUseCase()
            }.toMutableList()

            if (typeQuestion == Constant.AlphabetType.BOTH_HIRA_KATA) {
                getAllQuestion(allJapaneseAlphabet)
            } else {
                getSingleQuestion(allJapaneseAlphabet, typeQuestion)
            }

            mLoading.value = false
        }
    }

    private fun getAllQuestion(allJapaneseAlphabet: MutableList<AlphabetModel>) {
        val listQuestionHira = mutableListOf<AlphabetQuestionModel>()
        val listQuestionKata = mutableListOf<AlphabetQuestionModel>()
        for (i in 1..10) {
            val listData = allJapaneseAlphabet.filter {
                it.latin != null
            }
            val listShuffle = listData.shuffled().take(4)
            val listAnswer = listShuffle.shuffled()
            val question = listShuffle.first().latin
            val correctAnswer = listShuffle.first().hiragana

            listQuestionHira.add(
                AlphabetQuestionModel(
                    question,
                    correctAnswer,
                    listAnswer[0].hiragana,
                    listAnswer[1].hiragana,
                    listAnswer[2].hiragana,
                    listAnswer[3].hiragana
                )
            )

            allJapaneseAlphabet.removeIf { alphabet ->
                alphabet.latin == question
            }
        }

        for (i in 1..10) {
            val listData = allJapaneseAlphabet.filter {
                it.latin != null && it.katakana != null
            }
            val listShuffle = listData.shuffled().take(4)
            val listAnswer = listShuffle.shuffled()
            val question = listShuffle.first().latin
            val correctAnswer = listShuffle.first().katakana

            listQuestionKata.add(
                AlphabetQuestionModel(
                    question,
                    correctAnswer,
                    listAnswer[0].katakana,
                    listAnswer[1].katakana,
                    listAnswer[2].katakana,
                    listAnswer[3].katakana
                )
            )
        }

        val listTotal = listQuestionHira.zip(listQuestionKata).flatMap { (hira, kata) ->
            listOf(hira, kata)
        }
        listAlphabetQuestionModel.value = listTotal
    }

    private fun getSingleQuestion(
        allJapaneseAlphabet: MutableList<AlphabetModel>,
        typeQuestion: String
    ) {
        val listQuestion = mutableListOf<AlphabetQuestionModel>()

        while (listQuestion.size < Constant.AlphabetType.NUMBER_OF_QUESTION_ALPHABET) {
            val listData = if (typeQuestion == HIRAGANA_TYPE) {
                allJapaneseAlphabet.filter {
                    it.latin != null
                }
            } else {
                allJapaneseAlphabet.filter {
                    it.latin != null && it.katakana != null
                }
            }
            val listShuffle = listData.shuffled().take(4)
            val listAnswer = listShuffle.shuffled()
            val question = listShuffle.first().latin
            when (typeQuestion) {
                HIRAGANA_TYPE -> {
                    val correctAnswer = listShuffle.first().hiragana
                    listQuestion.add(
                        AlphabetQuestionModel(
                            question,
                            correctAnswer,
                            listAnswer[0].hiragana,
                            listAnswer[1].hiragana,
                            listAnswer[2].hiragana,
                            listAnswer[3].hiragana
                        )
                    )
                }

                KATAKANA_TYPE -> {
                    val correctAnswer = listShuffle.first().katakana
                    listQuestion.add(
                        AlphabetQuestionModel(
                            question,
                            correctAnswer,
                            listAnswer[0].katakana,
                            listAnswer[1].katakana,
                            listAnswer[2].katakana,
                            listAnswer[3].katakana
                        )
                    )
                }
            }

            allJapaneseAlphabet.removeIf { alphabet ->
                alphabet.latin == question
            }
        }

        listAlphabetQuestionModel.value = listQuestion
    }

    fun getVocabularyGame(unit: Int) {
        val vocabularyQuestionData = mutableListOf<VocabularyQuestionModel>()
        if (unit != Constant.VocabularyType.ALL_TYPE) {
            val params = GetDetailVocabularyUseCase.Input(unit)
            executeTask {
                val detail = withContext(Dispatchers.IO) {
                    getDetailVocabularyUseCase(params)
                }.toMutableList()
                listVocabularyGame.value = getDataQuestion(vocabularyQuestionData, detail)
            }
        } else {
            executeTask {
                val fullVocabulary = withContext(Dispatchers.IO) {
                    getVocabularyUseCase()
                }.toMutableList()
                listVocabularyGame.value = getDataQuestion(vocabularyQuestionData, fullVocabulary)
            }
        }
    }

    private fun getDataQuestion(
        vocabularyQuestionData: MutableList<VocabularyQuestionModel>,
        data: MutableList<VocabularyModel>
    ): List<VocabularyQuestionModel> {
        while (vocabularyQuestionData.size < Constant.VocabularyType.NUMBER_OF_QUESTION_ALPHABET) {
            val listData = data.filter {
                it.wordMeaning != null
            }
            val listShuffle = listData.shuffled().take(4)
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
                    listAnswer[3].wordMeaning
                )
            )
            data.removeIf {
                it.newWord == questionVocabulary
            }
        }
        return vocabularyQuestionData
    }
}
