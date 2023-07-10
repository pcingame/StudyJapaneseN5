package com.pc.studyjapanesen5.domain.usecase.vocabulary

import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.repository.VocabularyRepository
import com.pc.studyjapanesen5.domain.usecase.BaseUseCase

class GetDetailVocabularyUseCase(
    private val vocabularyRepository: VocabularyRepository
) :
    BaseUseCase<List<VocabularyModel>, GetDetailVocabularyUseCase.Input>() {

    override suspend fun invoke(input: Input): List<VocabularyModel> {
        return vocabularyRepository.getVocabularyByUnit(input.unit)
    }

    data class Input(val unit: Int)
}
