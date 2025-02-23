package com.example.videoplayer.domain.repository

import com.example.videoplayer.domain.model.videos.DataOfVideo

interface VideoRepository {

    suspend fun getVideos(): List<DataOfVideo>

}