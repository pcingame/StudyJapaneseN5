package com.pc.studyjapanesen5.repository.alphatbet

import com.pc.studyjapanesen5.model.AppDatabase
import com.pc.studyjapanesen5.model.entity.AlphabetEntity
import kotlinx.coroutines.flow.Flow

class AlphabetRepositoryImpl(database: AppDatabase) : AlphabetRepository {
    private val alphabetDao = database.alphabetDao()
    override fun getTypeCharacter(type: String): Flow<List<AlphabetEntity>> {
        return alphabetDao.getTypeCharacter(type)
    }
}
