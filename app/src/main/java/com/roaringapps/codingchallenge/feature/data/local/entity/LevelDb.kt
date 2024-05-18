package com.roaringapps.codingchallenge.feature.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
@Entity
data class LevelDb(
    val description: String,
    val state: String,
    val title: String,

    @PrimaryKey
    val level: String,
)