package com.pc.studyjapanesen5.data.repository.newword

import com.pc.studyjapanesen5.data.model.AppDatabase
import com.pc.studyjapanesen5.data.model.entity.WordEntity
import com.pc.studyjapanesen5.domain.repository.WordRepository
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
