package com.example.videoplayer.data.model.videos

import kotlinx.serialization.Serializable


@Serializable
data class LinkDTO(
    val rel: String,
    val uri: String
)