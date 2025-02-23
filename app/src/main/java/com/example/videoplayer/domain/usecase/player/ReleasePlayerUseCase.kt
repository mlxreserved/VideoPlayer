package com.example.videoplayer.domain.usecase.player

import com.example.videoplayer.domain.player.VideoPlayer
import javax.inject.Inject

class ReleasePlayerUseCase @Inject constructor(private val videoPlayer: VideoPlayer) {

    operator fun invoke() = videoPlayer.release()

}