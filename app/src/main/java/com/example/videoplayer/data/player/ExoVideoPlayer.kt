package com.example.videoplayer.data.player

import android.content.Context
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.HttpDataSource.HttpDataSourceException
import androidx.media3.datasource.HttpDataSource.InvalidResponseCodeException
import androidx.media3.decoder.DecoderException
import androidx.media3.exoplayer.ExoPlayer
import com.example.videoplayer.domain.customError.PlayVideoError
import com.example.videoplayer.domain.player.VideoPlayer
import java.net.SocketTimeoutException
import javax.inject.Inject

class ExoVideoPlayer @Inject constructor(
    private val context: Context
) : VideoPlayer {

    private var exoPlayer: ExoPlayer? = null
    private var errorHandler: ((PlayVideoError) -> Unit)? = null

    override fun play(videoUri: String) {
        exoPlayer = ExoPlayer.Builder(context).build()

        addErrorListener()

        val mediaItem = MediaItem.fromUri(videoUri)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.play()
    }

    override fun pause() {
        exoPlayer?.pause()
    }

    override fun release() {
        exoPlayer?.release()
    }

    override fun setErrorHandler(handler: (PlayVideoError) -> Unit) {
        this.errorHandler = handler
    }

    override fun getPlayer(): ExoPlayer? {
        return exoPlayer
    }

    private fun addErrorListener() {
        exoPlayer?.addListener(
            object : Player.Listener {
                @OptIn(UnstableApi::class)
                override fun onPlayerError(error: PlaybackException) {
                    when (val cause = error.cause){
                        is HttpDataSourceException -> {
                            if(cause is InvalidResponseCodeException) {
                                errorHandler?.invoke(PlayVideoError.HttpError(cause.responseCode, cause.message ?: "HTTP Error"))
                            } else {
                                errorHandler?.invoke(PlayVideoError.NetworkError)
                            }
                        }
                        is SocketTimeoutException -> {
                            errorHandler?.invoke(PlayVideoError.TimeoutError)
                        }
                        is DecoderException -> {
                            errorHandler?.invoke(PlayVideoError.DecodingError)
                        }
                        else -> {
                            errorHandler?.invoke(PlayVideoError.UnknownError(cause))
                        }
                    }
                }
            }
        )
    }
}