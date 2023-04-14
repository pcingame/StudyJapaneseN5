package com.pc.studyjapanesen5.model

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM vocabulary")
    fun getAll(): Flow<List<WordEntity>>

    @Upsert
    suspend fun upsertNewWord(newWord: WordEntity)
}
