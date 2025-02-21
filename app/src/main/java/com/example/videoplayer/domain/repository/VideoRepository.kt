package com.example.videoplayer.domain.repository

import com.example.videoplayer.domain.model.videoDetail.VideoDetail
import com.example.videoplayer.domain.model.videos.Videos

interface VideoRepository {

    suspend fun getVideoDetail(videoId: String): VideoDetail

    suspend fun getVideos(): Videos

}