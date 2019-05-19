package com.urbanist.music.feature.quests.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.urbanist.music.feature.map.domain.Event
import com.urbanist.music.feature.quests.domain.Quest
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class QuestsRepository @Inject constructor(
    firestore: FirebaseFirestore
) {

    private val subject: BehaviorSubject<List<Quest>> = BehaviorSubject.create()

    fun observeEvents(): Observable<List<Quest>> = subject

    init {
        firestore.collection("quests")
            .get()
            .addOnSuccessListener { snapshot ->
                val quests: ArrayList<Quest> = arrayListOf()

                snapshot.documents.forEach {
                    quests.add(it.toObject(Quest::class.java)!!)
                    Log.d("firestorage1", "${it.getString("name")}")
                }
                subject.onNext(quests)
            }
    }
}