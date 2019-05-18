package com.urbanist.music.feature.map.domain

import io.reactivex.Observable

interface EventsRepository {

    fun observeEvents(): Observable<List<Event>>
}