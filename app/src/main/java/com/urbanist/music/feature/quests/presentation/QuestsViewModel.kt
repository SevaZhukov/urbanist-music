package com.urbanist.music.feature.quests.presentation

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.urbanist.music.core.presentation.BaseViewModel
import com.urbanist.music.feature.quests.data.QuestsRepository
import com.urbanist.music.feature.quests.domain.Quest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class QuestsViewModel @Inject constructor(
    private val questsRepository: QuestsRepository
) : BaseViewModel() {
    val quests = MutableLiveData<List<Quest>>()

    override fun onBind(state: Bundle?) {
        super.onBind(state)
        questsRepository.observeEvents().observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                quests.value = it
            }, {
                it
            }).addTo(disposables)
    }
}