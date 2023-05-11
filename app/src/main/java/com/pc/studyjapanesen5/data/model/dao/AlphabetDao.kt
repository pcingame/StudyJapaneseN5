package com.pc.studyjapanesen5.data.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.pc.studyjapanesen5.data.model.entity.AlphabetEntity

@Dao
interface AlphabetDao {

    @Query("SELECT * FROM alphabet")
    suspend fun getTypeCharacter(): List<AlphabetEntity>

}
