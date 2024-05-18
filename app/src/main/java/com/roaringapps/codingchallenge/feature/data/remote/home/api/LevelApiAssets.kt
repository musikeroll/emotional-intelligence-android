package com.roaringapps.codingchallenge.feature.data.remote.home.api

import android.content.Context
import com.roaringapps.codingchallenge.feature.domain.model.EmotionLevels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    18/05/2024
 */
class LevelApiAssets(
    private val context: Context,
) {

    suspend fun findAll(): EmotionLevels? {

        var emotionLevels: EmotionLevels? = null
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets.open("activities.json")
            val size = withContext(Dispatchers.IO) {
                inputStream.available()
            }

            val buffer = ByteArray(size)
            withContext(Dispatchers.IO) {
                inputStream.read(buffer)
            }

            emotionLevels = EmotionLevels.parse(String(buffer))

        } catch (io: IOException) {
            Timber.d("Something went wrong: %s", io.message)
        } finally {
            withContext(Dispatchers.IO) {
                inputStream?.close()
            }
        }
        return emotionLevels
    }
}