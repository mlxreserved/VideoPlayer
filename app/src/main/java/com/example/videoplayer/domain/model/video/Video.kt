package com.example.videoplayer.domain.model.video

data class Video(
    val id: Int,
    val duration: Double,
    val thumbnail: String,
    val name: String,
    val asset: Asset
)