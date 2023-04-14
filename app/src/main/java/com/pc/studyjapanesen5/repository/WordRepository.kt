package com.pc.studyjapanesen5.repository

import com.pc.studyjapanesen5.model.WordEntity
import kotlinx.coroutines.flow.Flow

interface WordRepository {
    fun getAllVocabulary(): Flow<List<WordEntity>>

    suspend fun addNewWord(newWord: WordEntity)
}
