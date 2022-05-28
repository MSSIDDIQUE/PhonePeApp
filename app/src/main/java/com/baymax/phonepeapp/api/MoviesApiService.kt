package com.baymax.phonepeapp.api

import com.baymax.phonepeapp.api.movies_api_data.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//https://api.themoviedb.org/3/movie/popular?api_key=38a73d59546aa378980a88b645f487fc&language=en-US&page=1
interface MoviesApiService {
    @GET("popular")
    suspend fun getPopularMoviesList(
        @Query("api_key")
        key: String="38a73d59546aa378980a88b645f487fc"
    ): Response<MoviesResponse>
}