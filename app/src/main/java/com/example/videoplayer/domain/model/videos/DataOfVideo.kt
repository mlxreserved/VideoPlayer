package com.example.videoplayer.domain.model.videos

data class DataOfVideo(
    val duration: Double,
    val thumbnail: Thumbnail,
    val name: String,
    val assets: List<Asset>
)