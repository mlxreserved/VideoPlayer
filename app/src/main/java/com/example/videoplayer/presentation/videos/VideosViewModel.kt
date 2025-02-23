package com.example.videoplayer.presentation.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.domain.model.videos.DataOfVideo
import com.example.videoplayer.domain.model.videos.Videos
import com.example.videoplayer.domain.state.VideosState
import com.example.videoplayer.domain.usecase.videos.GetVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val getVideosUseCase: GetVideosUseCase
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

    private suspend fun getVideos(): List<DataOfVideo> = getVideosUseCase()
}
