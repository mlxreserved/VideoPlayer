package com.example.videoplayer.domain.local.repository

import com.example.videoplayer.domain.model.video.Video

interface VideoLocalRepository {

    suspend fun getLocalVideos(): List<Video>

    suspend fun insertLocalVideos(videos: List<Video>)

    suspend fun deleteLocalVideos()
}