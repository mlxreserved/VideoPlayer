package com.example.videoplayer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.videoplayer.data.local.dao.VideoDao
import com.example.videoplayer.data.local.entity.VideoEntity

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS VideoEntity_temp (
                id INTEGER PRIMARY KEY NOT NULL,
                duration REAL NOT NULL,
                thumbnail TEXT NOT NULL,
                name TEXT NOT NULL,
                asset_url TEXT NOT NULL,
                asset_contentType TEXT NOT NULL,
                asset_type TEXT NOT NULL
            )
        """)

        database.execSQL("""
            INSERT INTO VideoEntity_temp (id, duration, thumbnail, name, asset_url, asset_contentType, asset_type)
            SELECT id, duration, thumbnail, name, 
                   videoUrl, 
                   'video/mp4',
                   'MdMp4VideoFile'
            FROM VideoEntity
        """)

        database.execSQL("DROP TABLE VideoEntity")

        database.execSQL("ALTER TABLE VideoEntity_temp RENAME TO VideoEntity")
    }
}


@Database(
    entities = [VideoEntity::class],
    version = 2
)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
}