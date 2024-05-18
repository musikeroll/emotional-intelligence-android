package com.roaringapps.codingchallenge.feature.data.remote.home

import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import kotlinx.coroutines.flow.Flow

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
interface LevelsRepository {

    fun findAll(): Flow<EmotionLevels>
}