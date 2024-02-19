package com.sicredi.teste.centraldeeventos.data.api

import com.sicredi.teste.centraldeeventos.data.model.Event
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteEvent {

    fun observableEvents(): Observable<List<Event>> = EventApiInstance.instanceEventCall
        .getEvents()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}