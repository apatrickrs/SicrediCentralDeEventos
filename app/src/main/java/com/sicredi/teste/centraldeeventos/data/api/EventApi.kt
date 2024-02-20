package com.sicredi.teste.centraldeeventos.data.api

import com.sicredi.teste.centraldeeventos.data.model.CheckInEvent
import com.sicredi.teste.centraldeeventos.data.model.Event
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventApi {

    @GET("/events")
    fun getEvents() : Observable<List<Event>>

    @GET("/events/{id}")
    fun getEventById(@Path("id") id: Int) : Observable<Event>

    @POST("/checkin")
    fun doCheckinOnEvent(@Body checkInEvent: CheckInEvent) : Observable<String>
}