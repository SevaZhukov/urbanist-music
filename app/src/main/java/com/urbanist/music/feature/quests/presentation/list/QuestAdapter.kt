package com.urbanist.music.feature.quests.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.urbanist.music.R
import com.urbanist.music.databinding.ItemQuestBinding
import com.urbanist.music.feature.quests.domain.Quest


class QuestAdapter : RecyclerView.Adapter<QuestHolder>() {
    private val items = arrayListOf<Quest>()

    fun setData(quests: List<Quest>) {
        items.clear()
        items.addAll(quests)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemQuestBinding = DataBindingUtil.inflate(inflater, R.layout.item_quest, parent, false)
        return QuestHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: QuestHolder, position: Int) {
        holder.bind(items[position])
    }

}