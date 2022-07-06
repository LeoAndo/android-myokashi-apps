package com.leoleo2.myokashi.domain

import com.leoleo2.myokashi.OkashiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchOkashiUseCase(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val repository: OkashiRepository = OkashiRepository()
) {
    //  invokeで処理を書き、関数オブジェクトとして扱う
    suspend operator fun invoke(keyword: String): List<OkashiDomainModel>? {
        return withContext(dispatcher) {
            repository.searchOkashi(keyword)
        }
    }
}