package com.example.videoplayer.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideoEntity(
    @PrimaryKey
    val id: Int,
    val duration: Double,
    val thumbnail: String,
    val name: String,
    @Embedded
    val asset: AssetEntity
)
