package com.leoleo2.myokashi.domain

import com.leoleo2.myokashi.OkashiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 同じようなロジックを複数のViewModelで書きそうになったら、UseCaseクラスに処理を切り出すとよい。
 * 単純なアプリの場合はこのようなdomainレイヤーの処理は作成しない。
 * 利用例：
 * class MainViewModel constructor(private val useCase: SearchOkashiUseCase = SearchOkashiUseCase()) : ViewModel() {
 *      fun searchOkashi(keyword: String) {
 *          viewModelScope.launch {
 *              val response = useCase(keyword)
 *          }
 *      }
 * }
 */
class SearchOkashiUseCase(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: OkashiRepository = OkashiRepository()
) {
    //  invokeで処理を書き、関数オブジェクトとして扱う
    suspend operator fun invoke(keyword: String): List<OkashiDomainModel>? {
        return withContext(dispatcher) {
            null
            // repository.searchOkashi2(keyword)
        }
    }
}