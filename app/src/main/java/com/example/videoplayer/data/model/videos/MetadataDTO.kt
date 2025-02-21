package com.example.videoplayer.data.model.videos

import kotlinx.serialization.Serializable

@Serializable
data class MetadataDTO(
    val key: String,
    val value: String
)