package com.kaloricketabulky.ktlite.domain.usecase

import com.kaloricketabulky.ktlite.domain.repository.KTLiteRepository
import com.kaloricketabulky.ktlite.tools.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFoodDetailUseCase @Inject constructor(
    private val repository: KTLiteRepository
) {

    operator fun invoke(): Flow<Result<List<Int>>> = flow {

    }
}
