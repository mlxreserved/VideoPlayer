package com.example.videoplayer.presentation.videos.state

import com.example.videoplayer.domain.model.video.Video

sealed interface VideosState {
    data class Loaded (val videos: List<Video>): VideosState
    data class Error (val errorMessage: String): VideosState
    object Loading: VideosState
}