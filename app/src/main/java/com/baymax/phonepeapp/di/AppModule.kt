package com.baymax.phonepeapp.di

import android.app.Application
import android.content.Context
import com.baymax.phonepeapp.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
}