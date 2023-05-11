package com.pc.studyjapanesen5.domain.usecase.vocabulary

import com.pc.studyjapanesen5.domain.model.VocabularyModel
import com.pc.studyjapanesen5.domain.repository.VocabularyRepository
import com.pc.studyjapanesen5.domain.usecase.NoParamUseCase
import org.koin.core.component.inject

class GetVocabularyUseCase : NoParamUseCase<List<VocabularyModel>>() {

    private val vocabularyRepository: VocabularyRepository by inject()

    override suspend fun invoke(): List<VocabularyModel> {
        return vocabularyRepository.getAllVocabulary()
    }
}
