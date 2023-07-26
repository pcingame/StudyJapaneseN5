package com.pc.studyjapanesen5.domain.model

data class AlphabetQuestionModel(
    var question: String? = null,
    var correctAnswer: String? = null,
    var answer1: String? = null,
    var answer2: String? = null,
    var answer3: String? = null,
    var answer4: String? = null,
)

data class VocabularyQuestionModel(
    var vocabulary: String? = null,
    var kanji: String? = null,
    var correctAnswer: String? = null,
    var answer1: String? = null,
    var answer2: String? = null,
    var answer3: String? = null,
    var answer4: String? = null,
)

