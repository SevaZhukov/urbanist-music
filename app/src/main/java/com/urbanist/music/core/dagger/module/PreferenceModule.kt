package com.urbanist.music.core.dagger.module

import android.content.Context
import com.urbanist.music.core.dagger.scope.ActivityScope
import com.urbanist.music.core.domain.PreferenceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

    @Provides
    fun providePreferenceRepo(context: Context): PreferenceRepository = PreferenceRepository(context)
}