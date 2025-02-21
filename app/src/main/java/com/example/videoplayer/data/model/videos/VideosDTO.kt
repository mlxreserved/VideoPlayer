package com.example.videoplayer.data.model.videos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideosDTO(
    @SerialName("data")
    val dataOfVideosDTO: List<DataOfVideoDTO>,
    val pagination: PaginationDTO
)

