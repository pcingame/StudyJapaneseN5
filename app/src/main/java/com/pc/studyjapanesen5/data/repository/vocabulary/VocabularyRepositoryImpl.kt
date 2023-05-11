package com.pc.studyjapanesen5.data.repository.vocabulary

import com.pc.studyjapanesen5.data.model.AppDatabase
import com.pc.studyjapanesen5.data.model.entity.VocabularyEntity
import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.repository.VocabularyRepository


class VocabularyRepositoryImpl(private val database: AppDatabase) : VocabularyRepository {
    override suspend fun getAllVocabulary(): List<VocabularyModel> {
        return database.vocabularyDao().getAllVocabulary().map(VocabularyEntity::toModel)
    }
}
