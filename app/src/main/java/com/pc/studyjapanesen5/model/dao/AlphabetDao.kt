package com.pc.studyjapanesen5.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.pc.studyjapanesen5.model.entity.AlphabetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlphabetDao {

    @Query("SELECT * FROM alphabet WHERE type_character = :type")
    fun getTypeCharacter(type: String): Flow<List<AlphabetEntity>>

}
