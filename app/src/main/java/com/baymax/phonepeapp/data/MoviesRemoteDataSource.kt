package com.baymax.phonepeapp.data

import com.baymax.phonepeapp.api.MoviesApiService

class MoviesRemoteDataSource(
    private val moviesApiService: MoviesApiService
) : BaseDataSource() {

    suspend fun getPopularMoviesList() = getResult {
        moviesApiService.getPopularMoviesList()
    }
}