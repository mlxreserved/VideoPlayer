package com.example.videoplayer.domain.remote.repository

import com.example.videoplayer.domain.model.video.Video

interface VideoRemoteRepository {

    suspend fun getRemoteVideos(): List<Video>

}