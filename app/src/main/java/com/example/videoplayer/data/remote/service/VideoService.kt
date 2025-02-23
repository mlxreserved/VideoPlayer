package com.example.videoplayer.data.remote.service

import com.example.videoplayer.data.remote.model.RemoteVideoDTO
import com.example.videoplayer.util.Constant.API_TOKEN
import retrofit2.http.GET
import retrofit2.http.Header


interface VideoService {
    @GET("medias.json")
    suspend fun getRemoteVideos(
        @Header("Authorization") apiToken: String = "Bearer $API_TOKEN"
    ): List<RemoteVideoDTO>
}