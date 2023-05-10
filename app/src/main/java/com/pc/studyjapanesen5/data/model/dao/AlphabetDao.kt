package com.pc.studyjapanesen5.data.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.pc.studyjapanesen5.data.model.entity.AlphabetEntity

@Dao
interface AlphabetDao {

    @Query("SELECT * FROM alphabet WHERE type_character = :type")
    suspend fun getTypeCharacter(type: String): List<AlphabetEntity>

}
