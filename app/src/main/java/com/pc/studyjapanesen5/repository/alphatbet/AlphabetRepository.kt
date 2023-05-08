package com.pc.studyjapanesen5.repository.alphatbet

import com.pc.studyjapanesen5.model.entity.AlphabetEntity
import kotlinx.coroutines.flow.Flow

interface AlphabetRepository {

    fun getTypeCharacter(type: String): Flow<List<AlphabetEntity>>

}
