package com.example.videoplayer.data.mapper.remote.video

import com.example.videoplayer.data.remote.model.ThumbnailDTO
import com.example.videoplayer.domain.remote.model.Thumbnail

fun thumbnailDTO_to_thumbnail(thumbnailDTO: ThumbnailDTO): Thumbnail {
    return Thumbnail(
        url = thumbnailDTO.url
    )
}

fun thumbnail_to_thumbnailDTO(thumbnail: Thumbnail): ThumbnailDTO {
    return ThumbnailDTO(
        url = thumbnail.url
    )
}