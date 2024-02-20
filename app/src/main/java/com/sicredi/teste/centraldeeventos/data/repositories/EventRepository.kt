package com.sicredi.teste.centraldeeventos.data.repositories

import androidx.lifecycle.LiveData
import com.sicredi.teste.centraldeeventos.data.model.CheckInEvent
import com.sicredi.teste.centraldeeventos.data.model.Event

interface EventRepository {

    fun getEvents(): LiveData<List<Event>>

    fun getEventById(id: Int): LiveData<Event>

    fun doCheckinOnEvent(checkInEvent: CheckInEvent): LiveData<String>
}