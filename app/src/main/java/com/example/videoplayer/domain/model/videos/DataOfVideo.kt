package com.example.videoplayer.domain.model.videos

data class DataOfVideo(
    val assets: Assets,
    val title: String,
    val videoId: String,
    val duration: Int = -1
)