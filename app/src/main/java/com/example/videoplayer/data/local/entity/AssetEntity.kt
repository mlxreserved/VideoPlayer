package com.example.videoplayer.data.local.entity

import androidx.room.Entity

data class AssetEntity(
    val url: String,
    val contentType: String,
    val type: String
)
