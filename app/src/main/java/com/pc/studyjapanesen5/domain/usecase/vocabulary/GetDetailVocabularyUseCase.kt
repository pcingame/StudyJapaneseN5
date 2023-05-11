package com.pc.studyjapanesen5.domain.usecase.vocabulary

import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.repository.VocabularyRepository
import com.pc.studyjapanesen5.domain.usecase.BaseUseCase
import org.koin.core.component.inject

class GetDetailVocabularyUseCase :
    BaseUseCase<List<VocabularyModel>, GetDetailVocabularyUseCase.Input>() {

    private val vocabularyRepository: VocabularyRepository by inject()

    override suspend fun invoke(input: Input): List<VocabularyModel> {
        return vocabularyRepository.getVocabularyByUnit(input.unit)
    }

    data class Input(val unit: Int)
}
