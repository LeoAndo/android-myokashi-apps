package com.leoleo2.myokashi

sealed class UiState {
    object Initial : UiState()
    object Loading : UiState()
    data class Success(val items: List<APIResponse.Item>?) : UiState()
    data class Error(val errorMessage: String) : UiState()
}