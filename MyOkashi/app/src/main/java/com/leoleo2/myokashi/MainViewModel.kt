package com.leoleo2.myokashi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel constructor(private val repository: OkashiRepository = OkashiRepository()) :
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
            val response = repository.searchOkashi(keyword = keyword)
            // items.value = response.item
            uiState.value = UiState.Success(items = response.item)
        }
    }
}