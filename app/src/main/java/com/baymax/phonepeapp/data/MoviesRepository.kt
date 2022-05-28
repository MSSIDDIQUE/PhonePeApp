package com.baymax.phonepeapp.data

class MoviesRepository(private val remoteDataSource: MoviesRemoteDataSource) {
    suspend fun getPopularMoviesList() = remoteDataSource.getPopularMoviesList()
}