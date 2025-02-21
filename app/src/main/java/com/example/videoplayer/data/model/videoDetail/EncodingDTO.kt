package com.example.videoplayer.data.model.videoDetail

import kotlinx.serialization.Serializable


@Serializable
data class EncodingDTO(
    val metadata: MetadataVideoDetailDTO
)