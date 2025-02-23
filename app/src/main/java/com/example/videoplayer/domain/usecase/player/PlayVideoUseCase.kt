package com.example.videoplayer.domain.usecase.player

import com.example.videoplayer.domain.customError.PlayVideoError
import com.example.videoplayer.domain.player.VideoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayVideoUseCase @Inject constructor(
    private val videoPlayer: VideoPlayer
) {
    private val _errors = MutableSharedFlow<PlayVideoError>()
    val errors: SharedFlow<PlayVideoError> = _errors

    operator fun invoke(videoUri: String, coroutineScope: CoroutineScope) {
        videoPlayer.setErrorHandler { error ->
            coroutineScope.launch {
                _errors.emit(error)
            }
        }

        videoPlayer.play(videoUri)
    }

}