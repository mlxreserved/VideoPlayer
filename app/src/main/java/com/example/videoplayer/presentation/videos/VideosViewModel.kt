package com.example.videoplayer.presentation.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.domain.model.video.Video
import com.example.videoplayer.domain.usecase.local.DeleteLocalVideosUseCase
import com.example.videoplayer.domain.usecase.local.GetLocalVideosUseCase
import com.example.videoplayer.domain.usecase.local.InsertLocalVideosUseCase
import com.example.videoplayer.presentation.videos.state.VideosState
import com.example.videoplayer.domain.usecase.remote.videos.GetRemoteVideosUseCase
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
    private val getRemoteVideosUseCase: GetRemoteVideosUseCase,
    private val getLocalVideosUseCase: GetLocalVideosUseCase,
    private val deleteLocalVideosUseCase: DeleteLocalVideosUseCase,
    private val insertLocalVideosUseCase: InsertLocalVideosUseCase
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

            val remoteVideos = getRemoteVideosUseCase()
            deleteAndInsertLocalVideos(remoteVideos)

            _videosState.update { VideosState.Loaded(remoteVideos) }
        } catch (e: IOException) {
            handleError("Network error: ${e.message}")
        } catch (e: Exception) {
            handleError("An error occurred: ${e.message}")
        }
    }

    private suspend fun handleError(errorMessage: String) {
        val localVideos = getLocalVideosUseCase()
        if(localVideos.isNotEmpty()) {
            _videosState.update { VideosState.Loaded(localVideos) }
        } else {
            _videosState.update { VideosState.Error(errorMessage) }
        }
    }

    private suspend fun deleteAndInsertLocalVideos(videos: List<Video>) {
        deleteLocalVideosUseCase()
        insertLocalVideosUseCase(videos)
    }

}
