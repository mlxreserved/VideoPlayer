package com.example.videoplayer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.videoplayer.data.local.dao.VideoDao
import com.example.videoplayer.data.local.entity.VideoEntity

@Database(entities = [VideoEntity::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}