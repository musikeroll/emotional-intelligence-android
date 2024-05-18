package com.roaringapps.codingchallenge.feature

import com.roaringapps.codingchallenge.feature.data.local.entity.relational.LevelActivityRelationalDb
import com.roaringapps.codingchallenge.feature.data.local.mapper.EntityMapper
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import com.roaringapps.codingchallenge.feature.domain.model.Level

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
object EmotionLevelsRobot {

    fun data(): EmotionLevels = EmotionLevels(
        listOf(
            Level(
                emptyList(),
                title = "Find your tools",
                description = "Collect your personalised techniques to beat Anxiety",
                level = "1",
                state = "AVAILABLE",
            ),
            Level(
                emptyList(),
                title = "Understanding your anxiety",
                description = "Understanding anxiety is the first step towards overcoming it.",
                level = "2",
                state = "LOCKED",
            ),
            Level(
                emptyList(),
                title = "Building your toolbox",
                description = "Develop your first set of techniques to start fighting anxiety",
                level = "3",
                state = "LOCKED",
            )
        )
    )

    fun database(): List<LevelActivityRelationalDb> = listOf(
        LevelActivityRelationalDb(
            levelDb = EntityMapper.toDatabase(data().levels[0]),
            activityDbs = emptyList()
        ),
        LevelActivityRelationalDb(
            levelDb = EntityMapper.toDatabase(data().levels[1]),
            activityDbs = emptyList()
        ),
        LevelActivityRelationalDb(
            levelDb = EntityMapper.toDatabase(data().levels[2]),
            activityDbs = emptyList()
        )
    )
}