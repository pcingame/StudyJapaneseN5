package com.pc.studyjapanesen5.domain.repository

import com.pc.studyjapanesen5.data.model.entity.WordEntity
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getAllVocabulary(): Flow<List<WordEntity>>

    suspend fun addNewWord(newWord: WordEntity)
}
