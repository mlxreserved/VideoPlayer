package com.example.videoplayer.domain.remote.repository

import com.example.videoplayer.domain.remote.model.RemoteVideo

interface VideoRemoteRepository {

    suspend fun getRemoteVideos(): List<RemoteVideo>

}