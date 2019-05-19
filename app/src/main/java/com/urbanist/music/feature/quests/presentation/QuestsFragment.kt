package com.urbanist.music.feature.quests.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.urbanist.music.R
import com.urbanist.music.feature.quests.presentation.list.QuestAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_quests.*
import javax.inject.Inject

class QuestsFragment: DaggerFragment() {

    @Inject
    lateinit var viewModel: QuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar2.visibility = View.VISIBLE
        val layoutManager = LinearLayoutManager(activity)
        val adapter = QuestAdapter()
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
        viewModel.onBind()

        viewModel.quests.observe(this, Observer {
            adapter.setData(it)
            progressBar2.visibility = View.GONE
        })

    }
}