package com.urbanist.music.feature.quests.presentation.list

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.urbanist.music.R
import com.urbanist.music.core.pref.Fields
import com.urbanist.music.databinding.ItemQuestBinding
import com.urbanist.music.feature.quests.domain.Quest
import java.lang.StringBuilder

class QuestHolder(var binding: ItemQuestBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(quest: Quest) {

        Log.d("firestorage1", "${quest.name}")
        val stringBuilder = StringBuilder()
        quest.instruments.forEach {
            stringBuilder.append("$it ")
        }
        quest.genres.forEach {
            stringBuilder.append("$it ")
        }
        when (quest.instruments.last()) {
            Fields.STRINGS.title -> R.drawable.ic_strings
            Fields.DRUMS.title -> R.drawable.ic_drums
            Fields.WIND.title -> R.drawable.ic_wind
            Fields.FOLKS.title -> R.drawable.ic_folks
        }
        when (quest.genres.last()) {
            Fields.CLASSIC.title -> R.drawable.ic_classic
            Fields.ROCK.title -> R.drawable.ic_rock
            Fields.POP.title -> R.drawable.ic_pop
            Fields.JAZZ.title -> R.drawable.ic_jazz
        }
        val item = QuestItem(quest.name, stringBuilder.toString(), quest.count.toString(), R.drawable.ic_wind)
        binding.item = item
    }
}