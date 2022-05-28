package com.baymax.phonepeapp.data

sealed class UiState {
    data class Success<T>(val data: T) : UiState()
    data class Error(val msg: String) : UiState()
    data class Loading(val msg:String) : UiState()
    object Empty : UiState()
}
