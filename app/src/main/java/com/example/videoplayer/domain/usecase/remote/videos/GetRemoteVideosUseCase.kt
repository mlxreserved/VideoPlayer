package com.example.videoplayer.domain.usecase.remote.videos

import com.example.videoplayer.domain.remote.model.RemoteVideo
import com.example.videoplayer.domain.remote.repository.VideoRemoteRepository
import javax.inject.Inject


class GetRemoteVideosUseCase @Inject constructor(private val videoRepository: VideoRemoteRepository) {

    suspend operator fun invoke(): List<RemoteVideo> = videoRepository.getRemoteVideos()

}