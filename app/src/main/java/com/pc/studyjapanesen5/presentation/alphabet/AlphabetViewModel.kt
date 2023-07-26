package com.pc.studyjapanesen5.presentation.alphabet

import com.pc.studyjapanesen5.base.BaseViewModel
import com.pc.studyjapanesen5.common.Constant
import com.pc.studyjapanesen5.common.Constant.AlphabetType.COMBO_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.DAKUON_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.LONG_VOWEL_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.SINGLE_TYPE
import com.pc.studyjapanesen5.common.Constant.AlphabetType.SMALL_TYPE
import com.pc.studyjapanesen5.common.extension.partitions
import com.pc.studyjapanesen5.common.utils.SingleLiveData
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.model.AlphabetQuestionModel
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

    val listAlphabetQuestionModel = SingleLiveData<List<AlphabetQuestionModel>>()

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
                it.latin != null
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

        while (listQuestion.size < Constant.AlphabetType.NUMBER_OF_QUESTION) {
            val listData = allJapaneseAlphabet.filter {
                it.latin != null
            }
            val listShuffle = listData.shuffled().take(4)
            val listAnswer = listShuffle.shuffled()
            val question = listShuffle.first().latin
            when (typeQuestion) {
                Constant.AlphabetType.HIRAGANA_TYPE -> {
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

                Constant.AlphabetType.KATAKANA_TYPE -> {
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
}
