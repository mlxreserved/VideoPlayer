package com.example.videoplayer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.videoplayer.data.local.entity.VideoEntity

@Dao
interface VideoDao {
    @Query("SELECT * FROM videoentity")
    suspend fun getLocalVideos(): List<VideoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalVideos(videosEntity: List<VideoEntity>)

    @Query("DELETE FROM videoentity")
    suspend fun deleteLocalVideos()
}