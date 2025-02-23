package com.example.videoplayer.presentation.videos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videoplayer.data.mapper.remote.video.remoteVideos_to_localVideos
import com.example.videoplayer.domain.local.model.LocalVideo
import com.example.videoplayer.domain.remote.model.RemoteVideo
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

            val remoteVideos = getRemoteVideos()
            val localVideos = remoteVideos.map{ it.remoteVideos_to_localVideos() }
            deleteAndInsertLocalVideos(localVideos)

            _videosState.update { VideosState.Loaded(localVideos) }
        } catch (e: IOException) {
            handleError("Network error: ${e.message}")
        } catch (e: Exception) {
            handleError("An error occurred: ${e.message}")
        }
    }

    private suspend fun handleError(errorMessage: String) {
        val localVideos = getLocalVideos()
        if(localVideos.isNotEmpty()) {
            _videosState.update { VideosState.Loaded(localVideos) }
        } else {
            _videosState.update { VideosState.Error(errorMessage) }
        }
    }

    private suspend fun deleteAndInsertLocalVideos(videos: List<LocalVideo>) {
        deleteLocalVideos()
        insertLocalVideos(videos)
    }

    private suspend fun insertLocalVideos(videos: List<LocalVideo>) {
        insertLocalVideosUseCase(videos)
    }

    private suspend fun getLocalVideos(): List<LocalVideo> =
        getLocalVideosUseCase()

    private suspend fun deleteLocalVideos() {
        deleteLocalVideosUseCase()
    }

    private suspend fun getRemoteVideos(): List<RemoteVideo> =
        getRemoteVideosUseCase()
}
