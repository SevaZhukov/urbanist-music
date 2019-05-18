package com.urbanist.music.feature.report.data

import com.urbanist.music.feature.report.domain.model.Report
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST

interface ReportApi {
	@POST("reports")
	fun sendReport(@Body report: Report): Completable
}