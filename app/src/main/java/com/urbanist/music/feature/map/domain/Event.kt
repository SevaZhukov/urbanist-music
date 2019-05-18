package com.urbanist.music.feature.map.domain

data class Event(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val genres: List<String>
)