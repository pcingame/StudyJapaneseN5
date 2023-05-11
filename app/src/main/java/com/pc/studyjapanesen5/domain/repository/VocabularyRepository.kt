package com.pc.studyjapanesen5.domain.repository

import com.pc.studyjapanesen5.domain.model.VocabularyModel

interface VocabularyRepository {
    suspend fun getAllVocabulary(): List<VocabularyModel>

    suspend fun getVocabularyByUnit(unit: Int): List<VocabularyModel>
}
