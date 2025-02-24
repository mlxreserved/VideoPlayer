package com.example.videoplayer.data.remote.repository

import com.example.videoplayer.data.mapper.remote.video.remoteVideoDTO_to_video
import com.example.videoplayer.data.remote.service.VideoService
import com.example.videoplayer.domain.model.video.Video
import com.example.videoplayer.domain.remote.repository.VideoRemoteRepository
import javax.inject.Inject

class VideoRemoteRepositoryImpl @Inject constructor(private val videoService: VideoService) : VideoRemoteRepository {

    override suspend fun getRemoteVideos(): List<Video> {
        val remoteVideos = videoService.getRemoteVideos()
        return remoteVideos.map { remoteVideo -> remoteVideoDTO_to_video(remoteVideo) }
    }

}