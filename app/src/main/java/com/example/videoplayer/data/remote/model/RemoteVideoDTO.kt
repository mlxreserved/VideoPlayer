package com.example.videoplayer.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteVideoDTO(
    val id: Int,
    val duration: Double,
    val thumbnail: ThumbnailDTO,
    val name: String,
    val assets: List<AssetDTO>
)
