package com.urbanist.music.feature.quests.di

import com.google.firebase.firestore.FirebaseFirestore
import com.urbanist.music.core.dagger.scope.ActivityScope
import com.urbanist.music.feature.map.data.EventsRepositoryImpl
import com.urbanist.music.feature.map.domain.EventsRepository
import com.urbanist.music.feature.map.domain.GetEventsUseCase
import com.urbanist.music.feature.map.domain.GetEventsUseCaseImpl
import com.urbanist.music.feature.quests.data.QuestsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class QuestsModule {

    @ActivityScope
    @Provides
    fun provideQuestsRepo(firestore: FirebaseFirestore): QuestsRepository = QuestsRepository(firestore)

}