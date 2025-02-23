package com.example.videoplayer.data.repository

import com.example.videoplayer.data.api.VideoService
import com.example.videoplayer.data.mapper.videos.dataOfVideoDTO_to_dataOfVideo
import com.example.videoplayer.domain.model.videos.DataOfVideo
import com.example.videoplayer.domain.repository.VideoRepository
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(private val videoService: VideoService): VideoRepository {

    override suspend fun getVideos(): List<DataOfVideo> {
        val videos = videoService.getVideos()
        return videos.map { dataOfVideoDTO -> dataOfVideoDTO_to_dataOfVideo(dataOfVideoDTO) }
    }

}