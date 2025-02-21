package com.example.videoplayer.domain.usecase

import com.example.videoplayer.domain.repository.VideoRepository
import javax.inject.Inject

class GetVideoDetailUseCase @Inject constructor(private val videoRepository: VideoRepository) {

    suspend operator fun invoke(videoId: String) = videoRepository.getVideoDetail(videoId = videoId)

}