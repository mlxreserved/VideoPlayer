package com.example.videoplayer.data.api

import com.example.videoplayer.data.model.videos.DataOfVideoDTO
import com.example.videoplayer.util.Constants.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Header


interface VideoService {
    @GET("medias.json")
    suspend fun getVideos(
        @Header("Authorization") apiToken: String = "Bearer $API_TOKEN"
    ): List<DataOfVideoDTO>
}