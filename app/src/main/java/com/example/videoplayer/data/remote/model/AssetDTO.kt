package com.example.videoplayer.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AssetDTO (
    val url: String,
    val type: String,
    val contentType: String
)