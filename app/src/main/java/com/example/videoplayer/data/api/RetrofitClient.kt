package com.example.videoplayer.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitClient {
    private const val VIDEO_API_URL = "https://ws.api.video"

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    private val contentType = "application/json".toMediaType()
    private val converterFactory = json.asConverterFactory(contentType)

    // Предоставление доступа к api через Retrofit
    val videoService: VideoService by lazy {
        Retrofit.Builder()
            .baseUrl(VIDEO_API_URL)
            .addConverterFactory(converterFactory)
            .build()
            .create(VideoService::class.java)
    }
}