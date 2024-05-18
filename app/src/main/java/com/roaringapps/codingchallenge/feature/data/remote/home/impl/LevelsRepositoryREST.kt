package com.roaringapps.codingchallenge.feature.data.remote.home.impl

import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
import com.roaringapps.codingchallenge.feature.data.remote.home.api.LevelApiREST
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
class LevelsRepositoryREST(
    private val levelApiREST: LevelApiREST
) : LevelsRepository {

    override fun findAll(): Flow<EmotionLevels> = flow {
        levelApiREST.findAll()?.let { emotionLevels ->
            emit(emotionLevels)
        }
    }
}