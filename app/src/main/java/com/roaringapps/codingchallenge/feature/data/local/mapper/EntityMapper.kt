package com.roaringapps.codingchallenge.feature.data.local.mapper

import com.roaringapps.codingchallenge.feature.data.local.entity.ActivityDb
import com.roaringapps.codingchallenge.feature.data.local.entity.LevelDb
import com.roaringapps.codingchallenge.feature.data.local.entity.relational.LevelActivityRelationalDb
import com.roaringapps.codingchallenge.feature.domain.model.Activity
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import com.roaringapps.codingchallenge.feature.domain.model.Level

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
object EntityMapper {

    fun toDatabase(level: Level): LevelDb = LevelDb(
        description = level.description,
        state = level.state,
        title = level.title,
        level = level.level
    )

    fun toDatabase(activity: Activity, level: String): ActivityDb = ActivityDb(
        challengeId = activity.challengeId,
        description = activity.description,
        state = activity.state,
        title = activity.title,
        type = activity.type,
        id = activity.id,

        level = level
    )

    fun toDomain(relationalDbs: List<LevelActivityRelationalDb>): EmotionLevels = EmotionLevels(
        relationalDbs.map { relationalDb ->
            toDomain(
                relationalDb.levelDb,
                relationalDb.activityDbs
            )
        }
    )

    private fun toDomain(levelDb: LevelDb, activitiesDb: List<ActivityDb>): Level = Level(
        activities = activitiesDb.map { toDomain(it) },
        description = levelDb.description,
        level = levelDb.level,
        state = levelDb.state,
        title = levelDb.title
    )

    private fun toDomain(activityDb: ActivityDb): Activity = Activity(
        challengeId = activityDb.challengeId,
        description = activityDb.description,
        state = activityDb.state,
        title = activityDb.title,
        type = activityDb.type,
        id = activityDb.id,

        titleB = null,
        descriptionB = null,
        icon = null,
        lockedIcon = null
    )
}