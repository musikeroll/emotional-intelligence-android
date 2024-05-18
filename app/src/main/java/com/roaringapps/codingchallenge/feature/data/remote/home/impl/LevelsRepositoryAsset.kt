package com.roaringapps.codingchallenge.feature.data.remote.home.impl

import android.content.Context
import com.roaringapps.codingchallenge.feature.data.local.dao.LevelDao
import com.roaringapps.codingchallenge.feature.data.local.mapper.EntityMapper
import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
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
    private val context: Context,
    private val levelDao: LevelDao
) : LevelsRepository {

    override fun findAll(): Flow<EmotionLevels> = flow {
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets.open("activities.json")
            val size = inputStream.available()

            val buffer = ByteArray(size)
            inputStream.read(buffer)

            EmotionLevels.parse(String(buffer))
                .levels.forEach { level ->
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

        } catch (io: IOException) {
            Timber.d("Something went wrong: %s", io.message)
            emit(EmotionLevels(emptyList()))
        } finally {
            inputStream?.close()
        }
    }.flowOn(Dispatchers.IO)
}