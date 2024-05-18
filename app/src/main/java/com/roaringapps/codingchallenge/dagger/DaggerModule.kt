package com.roaringapps.codingchallenge.dagger

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.roaringapps.codingchallenge.feature.data.local.AppDatabase
import com.roaringapps.codingchallenge.feature.data.local.dao.LevelDao
import com.roaringapps.codingchallenge.feature.data.remote.home.api.LevelApiAssets
import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
import com.roaringapps.codingchallenge.feature.data.remote.home.impl.LevelsRepositoryAsset
import com.roaringapps.codingchallenge.feature.domain.usecase.FindAllLevels
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
@Module
@InstallIn(SingletonComponent::class)
class DaggerModule {

    @Provides
    @Singleton
    fun provideLevelsUseCase(levelsRepo: LevelsRepository): FindAllLevels =
        FindAllLevels(levelsRepo)

    @Provides
    @Singleton
    fun provideLevelsRepository(
        levelApiAssets: LevelApiAssets,
        levelDao: LevelDao,
    ): LevelsRepository = LevelsRepositoryAsset(levelApiAssets, levelDao)

    @Provides
    @Singleton
    fun provideLevelApiAssets(
        @ApplicationContext appContext: Context
    ): LevelApiAssets = LevelApiAssets(appContext)

    @Provides
    @Singleton
    fun provideDb(app: Application): AppDatabase = Room.databaseBuilder(
        app, AppDatabase::class.java, AppDatabase.DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideLevelDao(appDatabase: AppDatabase): LevelDao = appDatabase.levelDao

}