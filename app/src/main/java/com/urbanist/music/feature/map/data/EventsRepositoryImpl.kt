package com.urbanist.music.feature.map.data

import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.urbanist.music.feature.map.domain.Event
import com.urbanist.music.feature.map.domain.EventsRepository
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class EventsRepositoryImpl @Inject constructor(
    firestore: FirebaseFirestore
) : EventsRepository {

    init {
        firestore.collection("events")
            .document()
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("firestorage", "Listen failed.", e)
                }

                val source = if (snapshot != null && snapshot.metadata.hasPendingWrites())
                    "Local"
                else
                    "Server"

                if (snapshot != null && !snapshot.exists()) {
                    Log.d("firestorage", source + " data: " + snapshot.data)
                } else {
                    Log.d("firestorage", "$source data: null")
                }
            }

        firestore.collection("events")
            .get()
            .addOnSuccessListener { snapshot ->
                val events: ArrayList<Event> = arrayListOf()

                snapshot.documents.forEach {
                    events.add(getEvent(it))
                    Log.d("firestorage1", "${it.getString("name")}")
                }
                subject.onNext(events)
            }
    }

    private val subject: BehaviorSubject<List<Event>> = BehaviorSubject.create()

    override fun observeEvents(): Observable<List<Event>> = subject

    private fun getEvent(documentSnapshot: DocumentSnapshot): Event =
        Event(
            name = documentSnapshot.getString("name") ?: "",
            buskerName = documentSnapshot.getString("buskerName") ?: "",
            start = documentSnapshot.getString("start")?.toLong() ?: 0,
            end = documentSnapshot.getString("end")?.toLong() ?: 0,
            latitude = documentSnapshot.getString("latitude")?.toDouble() ?: 0.0,
            longitude = documentSnapshot.getString("longitude")?.toDouble() ?: 0.0,
            genres = listOf(),
            instruments = listOf(),
            props = documentSnapshot.getString("props") ?: ""
        )
}