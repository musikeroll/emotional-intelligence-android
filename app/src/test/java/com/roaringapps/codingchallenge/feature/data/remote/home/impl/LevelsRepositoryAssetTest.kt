package com.roaringapps.codingchallenge.feature.data.remote.home.impl

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.roaringapps.codingchallenge.feature.EmotionLevelsRobot
import com.roaringapps.codingchallenge.feature.data.local.dao.LevelDao
import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
import com.roaringapps.codingchallenge.feature.data.remote.home.api.LevelApiAssets
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * @author   RoRR <rollolpindo></rollolpindo>@gmail.com>
 * @since    18/05/2024
 */
@OptIn(ExperimentalCoroutinesApi::class)
class LevelsRepositoryAssetTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val dispatcher = UnconfinedTestDispatcher()
    private lateinit var levelsRepo: LevelsRepository

    @MockK
    lateinit var levelApiAssets: LevelApiAssets

    @MockK
    lateinit var levelDao: LevelDao

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        levelsRepo = LevelsRepositoryAsset(
            levelApiAssets, levelDao
        )
    }

    @After
    fun close() {
        Dispatchers.resetMain()
    }

    @Test
    fun `sort all returns empty`(): Unit = runTest{

        coEvery {
            levelApiAssets.findAll()
        } returns EmotionLevels(emptyList())

        coEvery {
            levelDao.findAll()
        } returns emptyList()

        levelsRepo.findAll().test {
            val emotionLevels = awaitItem()
            assertThat(emotionLevels.levels).isEmpty()
            awaitComplete()
        }
    }

    @Test
    fun `sort all returns item listing`(): Unit = runTest{

        coEvery {
            levelApiAssets.findAll()
        } returns EmotionLevelsRobot.data()

        coEvery {
            levelDao.insert(levelDb = any())
        } returns Unit

        coEvery {
            levelDao.insert(activityDb = any())
        } returns Unit

        coEvery {
            levelDao.findAll()
        } returns EmotionLevelsRobot.database()

        levelsRepo.findAll().test {
            val emotionLevels = awaitItem()
            assertThat(emotionLevels.levels).hasSize(3)
            awaitComplete()
        }
    }
}