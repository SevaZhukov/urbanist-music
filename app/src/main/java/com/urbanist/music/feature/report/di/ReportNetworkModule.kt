package com.urbanist.music.feature.report.di

import com.urbanist.music.core.dagger.scope.ActivityScope
import com.urbanist.music.feature.report.data.ReportApi
import com.urbanist.music.feature.report.domain.repository.ReportRepository
import com.urbanist.music.feature.report.domain.repository.impl.ReportRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ReportNetworkModule {
	@Provides
	@ActivityScope
	fun provideApi(retrofit: Retrofit): ReportApi = retrofit.create(ReportApi::class.java)

	@Provides
	@ActivityScope
	fun provideRepository(reportApi: ReportApi): ReportRepository = ReportRepositoryImpl(reportApi)
}