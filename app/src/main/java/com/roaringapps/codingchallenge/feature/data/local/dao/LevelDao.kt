package com.roaringapps.codingchallenge.feature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.roaringapps.codingchallenge.feature.data.local.entity.ActivityDb
import com.roaringapps.codingchallenge.feature.data.local.entity.LevelDb
import com.roaringapps.codingchallenge.feature.data.local.entity.relational.LevelActivityRelationalDb

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
@Dao
interface LevelDao {

    @Transaction
    @Query("SELECT * FROM LevelDb")
    fun findAll(): List<LevelActivityRelationalDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(levelDb: LevelDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activityDb: ActivityDb)

}