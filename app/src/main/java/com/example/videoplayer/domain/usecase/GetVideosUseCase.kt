package com.example.videoplayer.domain.usecase

import com.example.videoplayer.domain.model.videos.Videos
import com.example.videoplayer.domain.repository.VideoRepository
import javax.inject.Inject


class GetVideosUseCase @Inject constructor(private val videoRepository: VideoRepository) {

    suspend operator fun invoke(): Videos = videoRepository.getVideos()

}