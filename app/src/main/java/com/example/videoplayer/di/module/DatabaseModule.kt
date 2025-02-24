package com.example.videoplayer.di.module

import android.content.Context
import androidx.room.Room
import com.example.videoplayer.data.local.dao.VideoDao
import com.example.videoplayer.data.local.database.MIGRATION_1_2
import com.example.videoplayer.data.local.database.VideoDatabase
import com.example.videoplayer.data.local.repository.VideoLocalRepositoryImpl
import com.example.videoplayer.domain.local.repository.VideoLocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): VideoDatabase {
        return Room.databaseBuilder(
            context,
            VideoDatabase::class.java,
            "video_database"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideVideoDao(videoDatabase: VideoDatabase): VideoDao {
        return videoDatabase.videoDao()
    }

    @Provides
    fun provideVideoLocalRepository(videoDao: VideoDao): VideoLocalRepository {
        return VideoLocalRepositoryImpl(videoDao)
    }
}