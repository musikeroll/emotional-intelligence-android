package com.roaringapps.codingchallenge.feature.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = LevelDb::class,
            parentColumns = ["level"],
            childColumns = ["level"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ActivityDb(
    val challengeId: String,
    val description: String,
    val state: String,
    val title: String,
    val type: String,

    @PrimaryKey
    val id: String,

    @ColumnInfo(index = true)
    var level: String = ""
)
