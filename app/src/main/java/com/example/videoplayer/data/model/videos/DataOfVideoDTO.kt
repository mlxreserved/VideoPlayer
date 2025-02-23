package com.example.videoplayer.data.model.videos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataOfVideoDTO(
    val duration: Double,
    val thumbnail: ThumbnailDTO,
    val name: String,
    val assets: List<AssetDTO>
)
