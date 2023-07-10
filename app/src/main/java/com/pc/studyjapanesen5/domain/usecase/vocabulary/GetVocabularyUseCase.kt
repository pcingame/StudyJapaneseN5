package com.pc.studyjapanesen5.domain.usecase.vocabulary

import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.repository.VocabularyRepository
import com.pc.studyjapanesen5.domain.usecase.NoParamUseCase

class GetVocabularyUseCase (
    private val vocabularyRepository: VocabularyRepository
) : NoParamUseCase<List<VocabularyModel>>() {

    override suspend fun invoke(): List<VocabularyModel> {
        return vocabularyRepository.getAllVocabulary()
    }
}
