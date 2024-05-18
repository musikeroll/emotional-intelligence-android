package com.roaringapps.codingchallenge.feature.data.remote.home.impl

import com.roaringapps.codingchallenge.feature.data.local.dao.LevelDao
import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
import com.roaringapps.codingchallenge.feature.data.remote.home.api.LevelApiAssets
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

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
    }

    @After
    fun close() {
        Dispatchers.resetMain()
    }
}