package com.example.videoplayer.domain.usecase.local

import com.example.videoplayer.domain.local.repository.VideoLocalRepository
import javax.inject.Inject

class DeleteLocalVideosUseCase @Inject constructor(
    private val videoLocalRepository: VideoLocalRepository
) {

    suspend operator fun invoke() =
        videoLocalRepository.deleteLocalVideos()

}