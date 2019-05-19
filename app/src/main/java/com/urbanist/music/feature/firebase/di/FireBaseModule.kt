package com.urbanist.music.feature.firebase.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FireBaseModule {

    @Provides
    fun provideFireStorage(): FirebaseFirestore = FirebaseFirestore.getInstance()

}