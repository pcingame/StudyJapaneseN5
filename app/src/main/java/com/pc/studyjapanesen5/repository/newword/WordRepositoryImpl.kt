package com.pc.studyjapanesen5.repository.newword

import com.pc.studyjapanesen5.model.AppDatabase
import com.pc.studyjapanesen5.model.entity.WordEntity
import kotlinx.coroutines.flow.Flow


class WordRepositoryImpl(database: AppDatabase) : WordRepository {

    private val vocabularyDao = database.vocabularyDao()
    override fun getAllVocabulary(): Flow<List<WordEntity>> {
        return vocabularyDao.getAll()
    }

    override suspend fun addNewWord(newWord: WordEntity) {
        return vocabularyDao.upsertNewWord(newWord)
    }

}
