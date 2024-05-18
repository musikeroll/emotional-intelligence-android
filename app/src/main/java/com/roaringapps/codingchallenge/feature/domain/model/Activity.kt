package com.roaringapps.codingchallenge.feature.domain.model

data class Activity(
    val challengeId: String,
    val description: String,
    val descriptionB: Any?,
    val icon: Icon?,
    val id: String,
    val lockedIcon: LockedIcon?,
    val state: String,
    val title: String,
    val titleB: String?,
    val type: String
)