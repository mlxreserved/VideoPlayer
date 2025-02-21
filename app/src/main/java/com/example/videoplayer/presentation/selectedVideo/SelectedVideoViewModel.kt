package com.example.videoplayer.presentation.selectedVideo

import androidx.lifecycle.ViewModel
import com.example.videoplayer.domain.usecase.GetVideoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedVideoViewModel @Inject constructor(getVideoDetailUseCase: GetVideoDetailUseCase): ViewModel() {
}