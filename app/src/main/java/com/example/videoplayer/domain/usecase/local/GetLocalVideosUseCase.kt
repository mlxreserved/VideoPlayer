package com.example.videoplayer.domain.usecase.local

import com.example.videoplayer.domain.local.model.LocalVideo
import com.example.videoplayer.domain.local.repository.VideoLocalRepository
import javax.inject.Inject

class GetLocalVideosUseCase @Inject constructor(
    private val videoLocalRepository: VideoLocalRepository
) {

    suspend operator fun invoke(): List<LocalVideo> =
        videoLocalRepository.getLocalVideos()

}