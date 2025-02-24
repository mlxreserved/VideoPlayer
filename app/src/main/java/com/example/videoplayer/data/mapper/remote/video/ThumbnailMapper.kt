package com.example.videoplayer.data.mapper.remote.video

import com.example.videoplayer.data.remote.model.ThumbnailDTO

fun thumbnail_to_thumbnailDTO(thumbnailUrl: String): ThumbnailDTO {
    return ThumbnailDTO(
        url = thumbnailUrl
    )
}