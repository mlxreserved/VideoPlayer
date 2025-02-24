package com.example.videoplayer.presentation.selectedVideo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.exoplayer.ExoPlayer
import com.example.videoplayer.data.player.ExoVideoPlayer
import com.example.videoplayer.domain.customError.PlayVideoError
import com.example.videoplayer.domain.player.VideoPlayer
import com.example.videoplayer.domain.usecase.player.GetPlayerUseCase
import com.example.videoplayer.domain.usecase.player.PlayVideoUseCase
import com.example.videoplayer.domain.usecase.player.ReleasePlayerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectedVideoViewModel @Inject constructor(
    private val playVideoUseCase: PlayVideoUseCase,
    private val releasePlayerUseCase: ReleasePlayerUseCase,
    private val getPlayerUseCase: GetPlayerUseCase
): ViewModel() {
    private val _errors = MutableStateFlow<PlayVideoError?>(null)
    val errors: StateFlow<PlayVideoError?> = _errors.asStateFlow()

    private var exoPlayer: ExoPlayer? = null

    fun getPlayer(): ExoPlayer {
        initializeVideoPlayer()
        return exoPlayer ?: throw IllegalStateException("ExoPlayer is not initialized")
    }

    fun playVideo(videoUri: String) {
        if(exoPlayer?.isPlaying == true) {
            return
        }
        viewModelScope.launch {
            playVideoUseCase.errors.collect { error ->
                _errors.update { error }
            }
        }
        playVideoUseCase(videoUri, viewModelScope)
    }

    fun isVideoPlaying(): Boolean{
        return exoPlayer?.isPlaying ?: false
    }

    private fun releasePlayer() {
        releasePlayerUseCase()
    }

    override fun onCleared() {
        super.onCleared()
        releasePlayer()
    }

    fun initializeVideoPlayer() {
        if(exoPlayer == null) {
            exoPlayer = getPlayerUseCase()
        }
    }
}