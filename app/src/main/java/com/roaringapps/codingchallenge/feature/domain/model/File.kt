package com.roaringapps.codingchallenge.feature.domain.model

data class File(
    val contentType: String,
    val details: Details,
    val fileName: String,
    val url: String
)