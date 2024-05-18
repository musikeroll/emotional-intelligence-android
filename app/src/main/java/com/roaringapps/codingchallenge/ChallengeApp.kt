package com.roaringapps.codingchallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
@HiltAndroidApp
class ChallengeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.d("***  CONFIGURE DEBUG  ***")
    }
}