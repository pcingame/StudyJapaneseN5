package com.pc.studyjapanesen5.data.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.pc.studyjapanesen5.data.model.entity.VocabularyEntity

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary")
    suspend fun getAllVocabulary(): List<VocabularyEntity>

    @Query("SELECT * FROM vocabulary WHERE unit = :unit")
    suspend fun getVocabularyByUnit(unit: Int): List<VocabularyEntity>
}
