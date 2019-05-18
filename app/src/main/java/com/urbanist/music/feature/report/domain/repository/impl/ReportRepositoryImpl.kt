package com.urbanist.music.feature.report.domain.repository.impl

import com.urbanist.music.feature.report.data.ReportApi
import com.urbanist.music.feature.report.domain.repository.ReportRepository
import com.urbanist.music.feature.report.domain.model.Report
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

class ReportRepositoryImpl(private val reportApi: ReportApi) : ReportRepository {
	override fun sendReport(report: Report): Completable {
		return reportApi.sendReport(report)
			.observeOn(AndroidSchedulers.mainThread())
	}
}