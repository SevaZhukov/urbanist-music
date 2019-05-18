package com.urbanist.music.core.dagger

import android.content.Context
import com.urbanist.music.core.App
import com.urbanist.music.core.dagger.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<App> {

	@Component.Builder
	abstract class Builder : AndroidInjector.Builder<App>() {

		@BindsInstance
		abstract fun context(context: Context): Builder
	}
}