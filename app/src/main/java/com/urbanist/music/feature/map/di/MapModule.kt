package com.urbanist.music.feature.map.di

import com.urbanist.music.core.dagger.scope.ActivityScope
import com.urbanist.music.feature.map.data.EventsRepositoryImpl
import com.urbanist.music.feature.map.domain.EventsRepository
import com.urbanist.music.feature.map.domain.GetEventsUseCase
import com.urbanist.music.feature.map.domain.GetEventsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface MapModule {

    @Binds
    fun bindGetEventsUseCase(impl: GetEventsUseCaseImpl): GetEventsUseCase


    @Binds
    fun bindEventsRepository(impl: EventsRepositoryImpl): EventsRepository

}