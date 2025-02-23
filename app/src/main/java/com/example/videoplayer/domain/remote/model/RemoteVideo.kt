package com.example.videoplayer.domain.remote.model

data class RemoteVideo(
    val id: Int,
    val duration: Double,
    val thumbnail: Thumbnail,
    val name: String,
    val asset: Asset
)