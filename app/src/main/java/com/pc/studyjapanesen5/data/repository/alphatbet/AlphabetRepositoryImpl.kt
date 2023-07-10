package com.pc.studyjapanesen5.data.repository.alphatbet

import com.pc.studyjapanesen5.data.model.AppDatabase
import com.pc.studyjapanesen5.data.model.entity.AlphabetMapper
import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.repository.AlphabetRepository

class AlphabetRepositoryImpl(private val database: AppDatabase, private val mapper: AlphabetMapper) : AlphabetRepository {
    override suspend fun getTypeCharacter(): List<AlphabetModel> {
        return database.alphabetDao().getTypeCharacter().map{
            mapper.toModel(it)
        }
    }
}
