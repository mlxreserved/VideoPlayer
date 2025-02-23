package com.example.videoplayer.data.mapper.remote.video

import com.example.videoplayer.data.remote.model.RemoteVideoDTO
import com.example.videoplayer.domain.remote.model.Asset
import com.example.videoplayer.domain.remote.model.RemoteVideo
import com.example.videoplayer.util.Constant.SECURED_URL_START
import com.example.videoplayer.util.Constant.UNSECURED_URL_START

fun remoteVideoDTO_to_remoteVideo(remoteVideoDTO: RemoteVideoDTO): RemoteVideo {
    val assets: List<Asset> = remoteVideoDTO.assets.map { assetDTO -> assetDTO_to_asset(assetDTO) }
    val asset = findNecessaryAsset(assets = assets)
    val correctAsset = asset.copy(url = createCorrectUri(asset.url))

    return RemoteVideo(
        id = remoteVideoDTO.id,
        duration = remoteVideoDTO.duration,
        thumbnail = thumbnailDTO_to_thumbnail(remoteVideoDTO.thumbnail),
        name = remoteVideoDTO.name,
        asset = correctAsset
    )
}

fun remoteVideo_to_remoteVideoDTO(remoteVideo: RemoteVideo): RemoteVideoDTO {
    return RemoteVideoDTO(
        id = remoteVideo.id,
        duration = remoteVideo.duration,
        thumbnail = thumbnail_to_thumbnailDTO(remoteVideo.thumbnail),
        name = remoteVideo.name,
        assets = listOf(asset_to_assetDTO(remoteVideo.asset))
    )
}

private fun findNecessaryAsset(assets: List<Asset>): Asset {
    return assets.find { asset ->
        asset.contentType == "video/mp4" && asset.type == "MdMp4VideoFile"
                || asset.contentType == "video/mp4" && asset.type == "HdMp4VideoFile"
    } ?: Asset(
        url = assets[0].url,
        type = assets[0].type,
        contentType = assets[0].contentType
    )
}


private fun createCorrectUri(requiredUriMediaItem: String): String {
    if(requiredUriMediaItem.substring(0, 6) != SECURED_URL_START) {
        return requiredUriMediaItem.replace(UNSECURED_URL_START, SECURED_URL_START)
    }
    return requiredUriMediaItem
}

