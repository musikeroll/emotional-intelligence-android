package com.roaringapps.codingchallenge.feature.data.remote.home.api

import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
interface LevelApiREST {

    suspend fun findAll(): EmotionLevels?
}