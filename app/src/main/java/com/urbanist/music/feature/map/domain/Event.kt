package com.urbanist.music.feature.map.domain

data class Event(
    val name: String,
    val buskerName: String,
    val start: Long,
    val end: Long,
    val latitude: Double,
    val longitude: Double,
    val genres: List<String>,
    val instrument: List<String>,
    val props: String
)