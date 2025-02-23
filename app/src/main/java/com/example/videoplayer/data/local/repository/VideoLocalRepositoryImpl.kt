package com.example.videoplayer.data.local.repository

import com.example.videoplayer.data.local.dao.VideoDao
import com.example.videoplayer.data.local.entity.VideoEntity
import com.example.videoplayer.data.mapper.local.video.videoEntity_to_video
import com.example.videoplayer.data.mapper.local.video.video_to_videoEntity
import com.example.videoplayer.domain.local.model.LocalVideo
import com.example.videoplayer.domain.local.repository.VideoLocalRepository
import javax.inject.Inject

class VideoLocalRepositoryImpl @Inject constructor(private val videoDao: VideoDao) : VideoLocalRepository {
    override suspend fun getLocalVideos(): List<LocalVideo> {
        val videosEntity: List<VideoEntity> = videoDao.getLocalVideos()
        return videosEntity.map { videoEntity -> videoEntity_to_video(videoEntity) }
    }

    override suspend fun insertLocalVideos(videos: List<LocalVideo>) {
        val videosEntity: List<VideoEntity> = videos.map { video -> video_to_videoEntity(video) }
        videoDao.insertLocalVideos(videosEntity)
    }

    override suspend fun deleteLocalVideos() {
        videoDao.deleteLocalVideos()
    }
}