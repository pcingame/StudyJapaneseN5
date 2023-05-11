package com.pc.studyjapanesen5.domain.usecase.alphabet

import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.repository.AlphabetRepository
import com.pc.studyjapanesen5.domain.usecase.BaseUseCase
import com.pc.studyjapanesen5.domain.usecase.NoParamUseCase
import org.koin.core.component.inject

class GetAlphabetUseCase : NoParamUseCase<List<AlphabetModel>>() {
    private val alphabetRepository: AlphabetRepository by inject()
    override suspend fun invoke(): List<AlphabetModel> {
       return alphabetRepository.getTypeCharacter()
    }
}
