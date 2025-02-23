package com.example.videoplayer.data.model.videos

import kotlinx.serialization.Serializable

@Serializable
data class AssetDTO (
    val url: String,
    val type: String,
    val contentType: String
)