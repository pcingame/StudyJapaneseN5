package com.pc.studyjapanesen5.domain.usecase

import org.koin.core.component.KoinComponent

abstract class BaseUseCase<Output, in Input> : KoinComponent {
    abstract suspend operator fun invoke(input: Input): Output
}

abstract class NoParamUseCase<Output> : BaseUseCase<Output, Empty>() {
    override suspend fun invoke(input: Empty): Output {
        return invoke()
    }

    abstract suspend operator fun invoke(): Output
}
