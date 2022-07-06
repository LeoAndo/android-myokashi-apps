package com.leoleo2.myokashi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoleo2.myokashi.domain.SearchOkashiUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel constructor(private val usecase: SearchOkashiUseCase = SearchOkashiUseCase()) :
    ViewModel() {

    // val items = MutableLiveData<List<APIResponse.Item>?>()
    val uiState = MutableLiveData<UiState>(UiState.Initial)
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        val errorMessage = throwable.localizedMessage ?: "error"
        uiState.value = UiState.Error(errorMessage = errorMessage)
    }

    fun searchOkashi(keyword: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            uiState.value = UiState.Loading
            val response = usecase(keyword = keyword)
            // items.value = response.item
            uiState.value = UiState.Success(items = response)
        }
    }
}