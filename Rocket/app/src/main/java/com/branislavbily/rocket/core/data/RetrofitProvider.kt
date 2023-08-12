package com.branislavbily.rocket.core.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object RetrofitProvider {
    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    fun provide(): Retrofit {
        return try {
            val okhttp = OkHttpClient.Builder()
                .build()
            Retrofit.Builder()
                .baseUrl("https://api.spacexdata.com/v3/")
                .client(okhttp)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        } catch (e: Exception) {
            Retrofit.Builder()
                .build()
        }
    }
}
