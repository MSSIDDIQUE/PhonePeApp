package com.baymax.phonepeapp.ui.viewmodels

import androidx.lifecycle.*
import com.baymax.phonepeapp.data.MoviesRepository
import com.baymax.phonepeapp.data.Result
import com.baymax.phonepeapp.data.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeFragmentViewModel @Inject constructor(private val repo: MoviesRepository) : ViewModel() {

    private val _moviesData = MutableLiveData<UiState>(UiState.Empty)
    val moviesData: LiveData<UiState> = _moviesData

    suspend fun fetchPopularMoviesList() {
        when (val result = withContext(Dispatchers.IO) { repo.getPopularMoviesList() }) {
            is Result.Failure -> result.msg?.let { _moviesData.postValue(UiState.Error(it)) }
            is Result.Success -> _moviesData.postValue(UiState.Success(result.data))
        }
    }
}