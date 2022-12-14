package com.example.wgctestandroidapp.data.common.DI

import com.example.wgctestandroidapp.data.common.NullOnEmptyConverterFactory
import com.example.wgctestandroidapp.data.common.utils.NetworkConnectionInterceptor
import com.example.wgctestandroidapp.BuildConfig

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitDi {
    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient,
        nullConverterFactory: NullOnEmptyConverterFactory
    ): Retrofit {
        return  Retrofit.Builder().apply {
            baseUrl("https://static.leboncoin.fr/")
            addConverterFactory(ScalarsConverterFactory.create())
            addConverterFactory(nullConverterFactory)
            addConverterFactory(GsonConverterFactory.create(gson))
            client(client)
        }.build()

    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor

    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)

        }.also {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
            it.addInterceptor(logInterceptor)
        }.also {
            it.addInterceptor(networkConnectionInterceptor)
    }
            .build()
    }

}