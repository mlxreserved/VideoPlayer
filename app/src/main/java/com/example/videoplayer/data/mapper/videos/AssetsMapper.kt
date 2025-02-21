package com.example.videoplayer.data.mapper.videos

import com.example.videoplayer.data.model.videos.AssetsDTO
import com.example.videoplayer.domain.model.videos.Assets

fun assetsDTOTOAssets(assetsDTO: AssetsDTO): Assets {
    return Assets(
        mp4 = assetsDTO.mp4,
        player = assetsDTO.player,
        thumbnail = assetsDTO.thumbnail
    )
}