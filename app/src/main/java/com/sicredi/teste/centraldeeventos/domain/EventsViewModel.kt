package com.sicredi.teste.centraldeeventos.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicredi.teste.centraldeeventos.data.model.CheckInEvent
import com.sicredi.teste.centraldeeventos.data.repositories.EventRepository
import com.sicredi.teste.centraldeeventos.data.repositories.EventRepositoryImpl
import com.sicredi.teste.centraldeeventos.data.model.Event

class EventsViewModel(
    private val repository: EventRepository = EventRepositoryImpl()
): ViewModel() {

    fun getEvents(): LiveData<List<Event>> {
        return repository.getEvents()
    }

    fun getEventById(id: Int): LiveData<Event> {
        return repository.getEventById(id)
    }

    fun doCheckinOnEvent(checkInEvent: CheckInEvent): LiveData<String> {
        return repository.doCheckinOnEvent(checkInEvent)
    }
}