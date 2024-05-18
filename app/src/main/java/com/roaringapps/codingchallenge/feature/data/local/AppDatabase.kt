package com.roaringapps.codingchallenge.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roaringapps.codingchallenge.feature.data.local.dao.LevelDao
import com.roaringapps.codingchallenge.feature.data.local.entity.ActivityDb
import com.roaringapps.codingchallenge.feature.data.local.entity.LevelDb

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
@Database(
    version = 1,
    exportSchema = false,
    entities = [
        LevelDb::class,
        ActivityDb::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract val levelDao: LevelDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}