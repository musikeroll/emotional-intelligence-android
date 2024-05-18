package com.roaringapps.codingchallenge.feature.presentation.home

import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
data class HomeState(
    val emotionLevels: EmotionLevels = EmotionLevels(emptyList()),
    val loading: Boolean = false
)