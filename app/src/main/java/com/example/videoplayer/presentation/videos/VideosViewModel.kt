package com.example.videoplayer.presentation.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.domain.model.videoDetail.VideoDetail
import com.example.videoplayer.domain.model.videos.Videos
import com.example.videoplayer.domain.usecase.GetVideoDetailUseCase
import com.example.videoplayer.domain.usecase.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase,
    private val getVideoDetailUseCase: GetVideoDetailUseCase
): ViewModel() {

    private val _videosState: MutableStateFlow<VideosState> = MutableStateFlow(VideosState.Loading)
    val videosState: StateFlow<VideosState> = _videosState.asStateFlow()

    init {
        viewModelScope.launch {
            getVideosWithStateHandling()
        }
    }

    suspend fun getVideosWithStateHandling() {
        try {
            _videosState.update { VideosState.Loading }
            val videos = getVideos()
            _videosState.update { VideosState.Loaded(videos) }
        } catch (e: IOException) {
            _videosState.update { VideosState.Error("Network error: ${e.message}") }
        } catch (e: Exception) {
            _videosState.update { VideosState.Error("An error occurred: ${e.message}") }
        }
    }

    private suspend fun getVideos(): Videos {
        val videos = getVideosUseCase()

        val videosWithDuration = videos.dataOfVideos.map { dataOfVideo ->
            viewModelScope.async {
                val videoDuration = getVideoDuration(dataOfVideo.videoId)
                dataOfVideo.copy(duration = videoDuration)
            }
        }.awaitAll()

        return videos.copy(dataOfVideos = videosWithDuration)
    }

    private suspend fun getVideoDuration(videoId: String): Int {
        val videoDetail = getVideoDetailUseCase(videoId)
        val videoDuration = videoDetail.encoding.metadata.duration
        return videoDuration
    }
}

sealed interface VideosState {
    data class Loaded(val videos: Videos): VideosState
    data class Error(val errorMessage: String): VideosState
    object Loading: VideosState
}