package com.roaringapps.codingchallenge.feature.domain.model

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

data class EmotionLevels(
    val levels: List<Level>
) {
    companion object {
        fun parse(content: String): EmotionLevels {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return moshi
                .adapter(EmotionLevels::class.java)
                .fromJson(content) ?: EmotionLevels(emptyList())
        }
    }
}