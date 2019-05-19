package com.urbanist.music.feature.quests.presentation.list

import androidx.recyclerview.widget.RecyclerView
import com.urbanist.music.R
import com.urbanist.music.databinding.ItemQuestBinding
import com.urbanist.music.feature.quests.domain.Quest
import java.lang.StringBuilder

class QuestHolder(var binding: ItemQuestBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(quest: Quest) {
        val stringBuilder = StringBuilder()
        quest.instruments.forEach {
            stringBuilder.append("$it ")
        }
        quest.genres.forEach {
            stringBuilder.append("$it ")
        }
        val item = QuestItem(quest.name, stringBuilder.toString(), quest.count.toString(), R.drawable.ic_wind)
        binding.item = item
    }
}