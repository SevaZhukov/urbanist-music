package com.urbanist.music.feature.report.domain.repository

import com.urbanist.music.feature.report.domain.model.Report
import io.reactivex.Completable

interface ReportRepository {
	fun sendReport(report: Report): Completable
}