package com.pc.studyjapanesen5.domain.usecase.alphabet

import com.pc.studyjapanesen5.domain.model.AlphabetModel
import com.pc.studyjapanesen5.domain.repository.AlphabetRepository
import com.pc.studyjapanesen5.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetAlphabetUseCase : BaseUseCase<List<AlphabetModel>, GetAlphabetUseCase.Input>() {
    private val alphabetRepository: AlphabetRepository by inject()

    override suspend fun invoke(input: Input): List<AlphabetModel> {
        val type = input.type
        return alphabetRepository.getTypeCharacter(type)
    }

    data class Input(val type: String)

}
