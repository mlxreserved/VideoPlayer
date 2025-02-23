package com.example.videoplayer.presentation.videos.state

import com.example.videoplayer.domain.local.model.LocalVideo

sealed interface VideosState {
    data class Loaded (val videos: List<LocalVideo>): VideosState
    data class Error (val errorMessage: String): VideosState
    object Loading: VideosState
}