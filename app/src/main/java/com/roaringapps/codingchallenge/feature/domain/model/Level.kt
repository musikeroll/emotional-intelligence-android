package com.roaringapps.codingchallenge.feature.domain.model

data class Level(
    val activities: List<Activity>,
    val description: String,
    val level: String,
    val state: String,
    val title: String
)