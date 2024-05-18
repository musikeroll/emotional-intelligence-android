package com.roaringapps.codingchallenge.feature.data.local.entity.relational

import androidx.room.Embedded
import androidx.room.Relation
import com.roaringapps.codingchallenge.feature.data.local.entity.ActivityDb
import com.roaringapps.codingchallenge.feature.data.local.entity.LevelDb

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
data class LevelActivityRelationalDb(
    @Embedded val levelDb: LevelDb,

    @Relation(parentColumn = "level", entityColumn = "level")
    val activityDbs: List<ActivityDb>
)