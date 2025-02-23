package com.example.videoplayer.data.mapper.videos

import com.example.videoplayer.data.model.videos.AssetDTO
import com.example.videoplayer.domain.model.videos.Asset

fun assetDTO_to_asset(assetDTO: AssetDTO): Asset {
    return Asset(
        url = assetDTO.url,
        type = assetDTO.type,
        contentType = assetDTO.contentType
    )
}