package com.example.videoplayer.data.mapper.local.video

import com.example.videoplayer.data.local.entity.AssetEntity
import com.example.videoplayer.domain.model.video.Asset

fun assetEntity_to_asset(assetEntity: AssetEntity): Asset {
    return Asset(
        url = assetEntity.url,
        type = assetEntity.type,
        contentType = assetEntity.contentType
    )
}

fun asset_to_assetEntity(asset: Asset): AssetEntity {
    return AssetEntity(
        url = asset.url,
        type = asset.type,
        contentType = asset.contentType
    )
}