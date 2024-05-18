package com.roaringapps.codingchallenge.feature.presentation.home

import com.google.common.truth.Truth.assertThat
import com.roaringapps.codingchallenge.feature.EmotionLevelsRobot
import com.roaringapps.codingchallenge.feature.data.remote.home.LevelsRepository
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import com.roaringapps.codingchallenge.feature.domain.model.Level
import com.roaringapps.codingchallenge.feature.domain.usecase.FindAllLevels
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
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
class HomeViewModelTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private val dispatcher = StandardTestDispatcher()
    private lateinit var homeViewModel: HomeViewModel

    @MockK
    lateinit var levelsRepo: LevelsRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)

        coEvery {
            levelsRepo.findAll()
        } returns flow {
            emit(EmotionLevels(emptyList()))
        }

        homeViewModel = HomeViewModel(
            FindAllLevels(levelsRepo)
        )
    }

    @After
    fun close() {
        Dispatchers.resetMain()
    }

    @Test
    fun `list all returns empty`() {
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(homeViewModel.state.value.emotionLevels.levels).isEmpty()
    }

    @Test
    fun `sort all returns item listing`() {

        coEvery {
            levelsRepo.findAll()
        } returns flow {
            emit(EmotionLevelsRobot.data())
        }

        homeViewModel.findAll()
        dispatcher.scheduler.advanceUntilIdle()

        assertThat(homeViewModel.state.value.emotionLevels.levels).hasSize(3)

    }
}