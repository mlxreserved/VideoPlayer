package com.example.videoplayer.domain.state

import com.example.videoplayer.domain.model.videos.DataOfVideo
import com.example.videoplayer.domain.model.videos.Videos

sealed interface VideosState {
    data class Loaded(val videos: List<DataOfVideo>): VideosState
    data class Error(val errorMessage: String): VideosState
    object Loading: VideosState
}