package com.example.videoplayer.domain.local.repository

import com.example.videoplayer.domain.local.model.LocalVideo

interface VideoLocalRepository {

    suspend fun getLocalVideos(): List<LocalVideo>

    suspend fun insertLocalVideos(videos: List<LocalVideo>)

    suspend fun deleteLocalVideos()
}