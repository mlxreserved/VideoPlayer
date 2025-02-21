package com.example.videoplayer.data.repository

import com.example.videoplayer.data.api.VideoService
import com.example.videoplayer.data.mapper.videoDetail.videoDetailDTOToVideoDetail
import com.example.videoplayer.data.mapper.videos.videosDTOToVideos
import com.example.videoplayer.domain.model.videoDetail.VideoDetail
import com.example.videoplayer.domain.model.videos.Videos
import com.example.videoplayer.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(private val videoService: VideoService): VideoRepository {

    override suspend fun getVideoDetail(videoId: String): VideoDetail {
        val videoDetail = videoService.getVideoDetail(videoId = videoId)
        return videoDetailDTOToVideoDetail(videoDetail)
    }

    override suspend fun getVideos(): Videos {
        val videos = videoService.getVideos()
        return videosDTOToVideos(videos)
    }

}