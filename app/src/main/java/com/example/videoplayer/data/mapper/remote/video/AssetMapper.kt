package com.example.videoplayer.data.mapper.remote.video

import com.example.videoplayer.data.remote.model.AssetDTO
import com.example.videoplayer.domain.remote.model.Asset

fun assetDTO_to_asset(assetDTO: AssetDTO): Asset {
    return Asset(
        url = assetDTO.url,
        type = assetDTO.type,
        contentType = assetDTO.contentType
    )
}

fun asset_to_assetDTO(asset: Asset): AssetDTO {
    return AssetDTO(
        url = asset.url,
        type = asset.type,
        contentType = asset.contentType
    )
}