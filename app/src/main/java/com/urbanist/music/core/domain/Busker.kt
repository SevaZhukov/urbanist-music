package com.urbanist.music.core.domain

class Busker(
    val name: String?,
    val props: String?,
    val instruments: MutableSet<String>?,
    val genres: MutableSet<String>?,
    var longitude: String = "0",
    var latitude: String = "0"
)