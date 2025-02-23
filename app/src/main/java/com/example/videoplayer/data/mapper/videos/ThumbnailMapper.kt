package com.example.videoplayer.data.mapper.videos

import com.example.videoplayer.data.model.videos.ThumbnailDTO
import com.example.videoplayer.domain.model.videos.Thumbnail

fun thumbnailDTO_to_thumbnail(thumbnailDTO: ThumbnailDTO): Thumbnail {
    return Thumbnail(
        url = thumbnailDTO.url
    )
}