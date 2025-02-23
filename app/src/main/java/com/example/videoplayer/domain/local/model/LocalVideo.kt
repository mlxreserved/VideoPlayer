package com.example.videoplayer.domain.local.model

data class LocalVideo(
    val id: Int,
    val duration: Double,
    val thumbnail: String,
    val name: String,
    val videoUri: String
)