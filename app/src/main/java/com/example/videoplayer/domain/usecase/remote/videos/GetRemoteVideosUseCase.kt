package com.example.videoplayer.domain.usecase.remote.videos

import com.example.videoplayer.domain.model.video.Video
import com.example.videoplayer.domain.remote.repository.VideoRemoteRepository
import javax.inject.Inject


class GetRemoteVideosUseCase @Inject constructor(private val videoRemoteRepository: VideoRemoteRepository) {

    suspend operator fun invoke(): List<Video> = videoRemoteRepository.getRemoteVideos()

}