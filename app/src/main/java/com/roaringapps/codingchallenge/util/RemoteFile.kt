package com.roaringapps.codingchallenge.util

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.buffer
import okio.sink
import timber.log.Timber
import java.io.File
import java.io.IOException

/**
 * @author   RoRR <rollolpindo@gmail.com>
 * @since    17/05/2024
 */
class RemoteFile(
    private val context: Context
) {

    enum class Status {
        DOWNLOADED, SKIPPED, ERROR
    }

    fun download(fileUrl: String, filename: String, filepath: String): Status {

        try {
            if (fileUrl.isEmpty()) {
                return Status.SKIPPED
            }

            val imageFile = File(filepath, filename)
            if (imageFile.exists() && !imageFile.createNewFile()) {
                return Status.SKIPPED
            }


            val request = Request.Builder().url(fileUrl).build()
            val response = OkHttpClient.Builder().build()
                .newCall(request)
                .execute()

            response.body?.let {
                val sink = imageFile.sink().buffer()
                sink.writeAll(it.source())
                sink.close()
                it.close()
            }

            return Status.DOWNLOADED

        } catch (io: IOException) {
            Timber.d("Download Failed: %s - %s", io.message, filename)
        } catch (e: Exception) {
            Timber.e(e, "Download Error: %s", e.message)
        }

        return Status.ERROR
    }
}