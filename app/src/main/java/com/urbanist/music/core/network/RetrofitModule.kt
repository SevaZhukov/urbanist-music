package com.urbanist.music.core.network

import android.content.Context
import com.urbanist.music.BuildConfig
import com.urbanist.music.core.domain.PreferenceRepository
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitModule {

	private val okHttpClient = OkHttpClient.Builder()
		.followSslRedirects(true)
		.addInterceptor(getInterceptor())
		.build()

	@Provides
	@Singleton
	fun providesRetrofit(): Retrofit =
		Retrofit.Builder()
			.baseUrl(BuildConfig.BACKEND_ENDPOINT)
			.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()

	private fun getInterceptor(): Interceptor {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY
		return interceptor
	}

	@Provides
	@Singleton
	fun preferenceRetrofit(context: Context): PreferenceRepository = PreferenceRepository(context)
}