package com.example.videoplayer.domain.usecase.local

import com.example.videoplayer.domain.local.repository.VideoLocalRepository
import com.example.videoplayer.domain.model.video.Video
import javax.inject.Inject

class InsertLocalVideosUseCase @Inject constructor(
    private val videoLocalRepository: VideoLocalRepository
) {

    suspend operator fun invoke(videos: List<Video>) =
        videoLocalRepository.insertLocalVideos(videos)

}