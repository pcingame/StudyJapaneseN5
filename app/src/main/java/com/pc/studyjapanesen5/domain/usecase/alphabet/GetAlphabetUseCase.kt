package com.pc.studyjapanesen5.domain.usecase.alphabet

import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.repository.AlphabetRepository
import com.pc.studyjapanesen5.domain.usecase.NoParamUseCase

class GetAlphabetUseCase(
    private val alphabetRepository: AlphabetRepository
) : NoParamUseCase<List<AlphabetModel>>() {
    override suspend fun invoke(): List<AlphabetModel> {
       return alphabetRepository.getTypeCharacter()
    }
}
