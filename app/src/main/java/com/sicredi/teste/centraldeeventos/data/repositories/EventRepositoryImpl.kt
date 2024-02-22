package com.sicredi.teste.centraldeeventos.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sicredi.teste.centraldeeventos.data.api.RemoteEvent
import com.sicredi.teste.centraldeeventos.data.model.CheckInEvent
import com.sicredi.teste.centraldeeventos.data.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class EventRepositoryImpl : EventRepository {

    private val compositeDisposable = CompositeDisposable()
    private val remoteEvents = RemoteEvent()

    override fun getEvents(): LiveData<List<Event>> {
        val events = MutableLiveData<List<Event>>()

        val disposableObserver = remoteEvents.observableAllEvents()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Event>>() {

                override fun onComplete() { }

                override fun onNext(response: List<Event>) {
                    events.postValue(response)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    events.postValue(emptyList())
                }
            })

        disposableObserver?.let { compositeDisposable.add(it) }

        return events
    }

    override fun getEventById(id: Int): LiveData<Event> {
        val event = MutableLiveData<Event>()

        val disposableObserver = remoteEvents.observableEventById(id)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<Event>() {

                override fun onComplete() { }

                override fun onNext(response: Event) {
                    event.postValue(response)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

        disposableObserver?.let { compositeDisposable.add(it) }

        return event
    }

    override fun doCheckinOnEvent(checkInEvent: CheckInEvent): LiveData<String> {
        val checkin = MutableLiveData<String>()

        val disposableObserver = remoteEvents.observableDoCheckinOnEvent(checkInEvent)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<String>() {

                override fun onComplete() { }

                override fun onNext(response: String) {
                    checkin.postValue(response)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

        disposableObserver?.let { compositeDisposable.add(it) }

        return checkin
    }
}