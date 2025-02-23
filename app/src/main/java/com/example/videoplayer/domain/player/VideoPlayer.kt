package com.example.videoplayer.domain.player

import androidx.media3.exoplayer.ExoPlayer
import com.example.videoplayer.domain.customError.PlayVideoError

interface VideoPlayer {
    fun play(videoUri: String)
    fun pause()
    fun release()
    fun setErrorHandler(handler: (PlayVideoError) -> Unit)
    fun getPlayer(): ExoPlayer?
}