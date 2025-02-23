package com.example.videoplayer.domain.usecase.player

import androidx.media3.exoplayer.ExoPlayer
import com.example.videoplayer.domain.player.VideoPlayer
import javax.inject.Inject

class GetPlayerUseCase @Inject constructor(
    private val videoPlayer: VideoPlayer
) {

    operator fun invoke(): ExoPlayer? = videoPlayer.getPlayer()

}