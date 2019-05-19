package com.urbanist.music.feature.quests.domain

class Quest() {
    lateinit var name: String
    var count: Int = 0
    lateinit var genres: List<String>
    lateinit var instruments: List<String>

    constructor(name: String,
                count: Int,
                genres: List<String>,
                instruments: List<String>) : this() {
        this.name = name
        this.count = count
        this.genres = genres
        this.instruments = instruments
    }
}