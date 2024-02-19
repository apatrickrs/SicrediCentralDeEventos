package com.sicredi.teste.centraldeeventos.data.api

import com.sicredi.teste.centraldeeventos.data.model.Event
import io.reactivex.Observable
import retrofit2.http.GET

interface EventApi {

    @GET("/events")
    fun getEvents() : Observable<List<Event>>
}