package com.pc.studyjapanesen5.repository

import com.pc.studyjapanesen5.model.AppDatabase
import com.pc.studyjapanesen5.model.WordEntity
import kotlinx.coroutines.flow.Flow


class WordRepositoryImpl(val database: AppDatabase) : WordRepository {

    private val vocabularyDao = database.vocabularyDao()
    override fun getAllVocabulary(): Flow<List<WordEntity>> {
        return vocabularyDao.getAll()
    }

    override suspend fun addNewWord(newWord: WordEntity) {
        return vocabularyDao.upsertNewWord(newWord)
    }

}
