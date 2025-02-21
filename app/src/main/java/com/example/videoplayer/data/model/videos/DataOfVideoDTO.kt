package com.example.videoplayer.data.model.videos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataOfVideoDTO(
    val assets: AssetsDTO,
    val deletesAt: String?,
    val description: String?,
    val discarded: Boolean?,
    val discardedAt: String?,
    val language: String?,
    val languageOrigin: String?,
    val metadata: List<MetadataDTO>,
    val mp4Support: Boolean,
    val panoramic: Boolean,
    val playerId: String? = "",
    @SerialName("public")
    val isPublic: Boolean?,
    val publishedAt: String,
    val source: SourceDTO,
    val tags: List<String>?,
    val title: String,
    val updatedAt: String?,
    val videoId: String
)