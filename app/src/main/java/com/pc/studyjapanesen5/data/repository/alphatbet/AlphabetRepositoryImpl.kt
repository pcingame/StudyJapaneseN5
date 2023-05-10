package com.pc.studyjapanesen5.data.repository.alphatbet

import com.pc.studyjapanesen5.data.model.AppDatabase
import com.pc.studyjapanesen5.data.model.entity.AlphabetEntity
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.repository.AlphabetRepository

class AlphabetRepositoryImpl(private val database: AppDatabase) : AlphabetRepository {
    override suspend fun getTypeCharacter(type: String): List<AlphabetModel> {
        return database.alphabetDao().getTypeCharacter(type).map(AlphabetEntity::toModel)
    }
}
