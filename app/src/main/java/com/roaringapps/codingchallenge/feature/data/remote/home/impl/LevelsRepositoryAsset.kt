package com.roaringapps.codingchallenge.feature.data.remote.home.impl

import android.content.Context
import com.roaringapps.codingchallenge.feature.data.local.dao.LevelDao
import com.roaringapps.codingchallenge.feature.data.local.mapper.EntityMapper
import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
import com.roaringapps.codingchallenge.feature.data.remote.home.api.LevelApiAssets
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
class LevelsRepositoryAsset(
    private val levelApiAssets: LevelApiAssets,
    private val levelDao: LevelDao
) : LevelsRepository {

    override fun findAll(): Flow<EmotionLevels> = flow {

        val emotionLevels = levelApiAssets.findAll()
        if (emotionLevels != null) {
            emotionLevels.levels.forEach { level ->
                levelDao.insert(
                    EntityMapper.toDatabase(level)
                )

                level.activities.forEach { activity ->
                    levelDao.insert(
                        EntityMapper.toDatabase(activity, level.level)
                    )
                }
            }

            emit(
                EntityMapper.toDomain(
                    levelDao.findAll()
                )
            )
        } else {
            emit(EmotionLevels(emptyList()))
        }
    }.flowOn(Dispatchers.IO)
}