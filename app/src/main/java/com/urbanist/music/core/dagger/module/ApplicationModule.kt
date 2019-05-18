package com.urbanist.music.core.dagger.module

import com.urbanist.music.core.dagger.scope.ActivityScope
import com.urbanist.music.core.network.RetrofitModule
import com.urbanist.music.feature.firebase.di.FireBaseModule
import com.urbanist.music.feature.map.di.MapModule
import com.urbanist.music.feature.map.presentation.MapsActivity
import com.urbanist.music.feature.report.ReportActivity
import com.urbanist.music.feature.report.di.ReportNetworkModule
import com.urbanist.music.feature.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class,
        FireBaseModule::class
    ]
)
interface ApplicationModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ReportNetworkModule::class])
    fun reportActivityInjector(): ReportActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MapModule::class])
    fun mapsActivityInjector(): MapsActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun splashActivityInjector(): SplashActivity
}