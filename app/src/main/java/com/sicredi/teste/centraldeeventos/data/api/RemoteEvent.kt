package com.sicredi.teste.centraldeeventos.data.api

import com.sicredi.teste.centraldeeventos.data.model.CheckInEvent
import com.sicredi.teste.centraldeeventos.data.model.Event
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteEvent {

    fun observableAllEvents(): Observable<List<Event>> = EventApiInstance.instanceEventCall
        .getEvents()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun observableEventById(id: Int): Observable<Event> = EventApiInstance.instanceEventCall
        .getEventById(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun observableDoCheckinOnEvent(checkInEvent: CheckInEvent): Observable<String> = EventApiInstance.instanceEventCall
        .doCheckinOnEvent(checkInEvent)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}