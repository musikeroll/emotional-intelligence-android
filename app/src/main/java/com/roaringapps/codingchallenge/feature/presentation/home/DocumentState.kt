package com.roaringapps.codingchallenge.feature.presentation.home

import android.graphics.Bitmap
import com.roaringapps.codingchallenge.feature.domain.model.Activity

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
data class DocumentState(
    val activity: Activity? = null,
    val bitmap: Bitmap? = null
)
