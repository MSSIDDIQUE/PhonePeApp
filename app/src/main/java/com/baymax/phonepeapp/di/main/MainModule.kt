package com.baymax.phonepeapp.di.main

import com.baymax.phonepeapp.api.MoviesApiService
import com.baymax.phonepeapp.api.movies_api_data.Constants
import com.baymax.phonepeapp.data.MoviesRemoteDataSource
import com.baymax.phonepeapp.data.MoviesRepository
import com.baymax.weatherforecast.di.main.MainScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideWeatherApiServices(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ): MoviesApiService {
        return getDynamicRetrofitClient(
            client,
            converterFactory,
            Constants.MOVIES_API_BASE_URL
        ).create(MoviesApiService::class.java)
    }

    @MainScope
    @Provides
    fun provideRemoteDataSource(
        weatherApiService: MoviesApiService
    ): MoviesRemoteDataSource {
        return MoviesRemoteDataSource(weatherApiService)
    }

    @MainScope
    @Provides
    fun provideRepository(
        remoteDataSource: MoviesRemoteDataSource
    ): MoviesRepository {
        return MoviesRepository(
            remoteDataSource
        )
    }

    private fun getDynamicRetrofitClient(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
    }
}