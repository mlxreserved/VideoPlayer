package com.example.videoplayer.data.api

import com.example.videoplayer.data.model.videoDetail.VideoDetailDTO
import com.example.videoplayer.data.model.videos.VideosDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

private const val API_TOKEN = "HenJ38d0hZMtdTXRidlYtnqWoBjno4JJ1xs7DR1bIJv"

interface VideoService {
    @GET("/videos")
    suspend fun getVideos(
        @Header("Authorization") token: String = "Bearer $API_TOKEN"
    ): VideosDTO

    @GET("/videos/{videoId}/status")
    suspend fun getVideoDetail(
        @Header("Authorization") token: String = "Bearer $API_TOKEN",
        @Path("videoId") videoId: String
    ): VideoDetailDTO
}