package com.pc.studyjapanesen5.domain.repository

import com.pc.studyjapanesen5.domain.model.AlphabetModel

interface AlphabetRepository {

    suspend fun getTypeCharacter(): List<AlphabetModel>

}
