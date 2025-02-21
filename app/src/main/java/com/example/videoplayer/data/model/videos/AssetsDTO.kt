package com.example.videoplayer.data.model.videos

import kotlinx.serialization.Serializable

@Serializable
data class AssetsDTO(
    val hls: String,
    val iframe: String,
    val mp4: String,
    val player: String,
    val thumbnail: String
)
