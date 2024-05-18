package com.roaringapps.codingchallenge.feature.domain.usecase

import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import kotlinx.coroutines.flow.Flow

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
class FindAllLevels(
    private val levelsRepo: LevelsRepository
) {

    operator fun invoke(): Flow<EmotionLevels> = levelsRepo.findAll()

}