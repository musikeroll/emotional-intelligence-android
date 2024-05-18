package com.roaringapps.codingchallenge.feature.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roaringapps.codingchallenge.feature.domain.usecase.FindAllLevels
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    16/05/2024
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val findAllLevels: FindAllLevels
): ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

//    private val _bitmapState = mutableStateOf(DocumentState())
//    val bitmapState: State<DocumentState> = _bitmapState

    private var findAllJob: Job? = null

    init {
        findAll()
    }

    fun findAll() {
        _state.value = state.value.copy(
            loading = true
        )

        findAllJob?.cancel()
        findAllJob = findAllLevels.invoke()
            .onEach { levels ->
                _state.value = state.value.copy(
                    emotionLevels = levels,
                    loading = false
                )
            }
            .launchIn(viewModelScope)
    }
}