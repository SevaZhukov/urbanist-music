package com.urbanist.music.feature.map.domain

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface GetEventsUseCase {
    operator fun invoke(): Observable<List<Event>>
}

class GetEventsUseCaseImpl @Inject constructor(
    private val eventsRepository: EventsRepository
) : GetEventsUseCase {


    override fun invoke(): Observable<List<Event>> =
        eventsRepository.observeEvents()
            .subscribeOn(Schedulers.io())
}