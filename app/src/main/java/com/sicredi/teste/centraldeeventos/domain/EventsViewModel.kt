package com.sicredi.teste.centraldeeventos.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sicredi.teste.centraldeeventos.data.EventRepository
import com.sicredi.teste.centraldeeventos.data.EventRepositoryImpl
import com.sicredi.teste.centraldeeventos.data.model.Event

class EventsViewModel(
    private val repository: EventRepository = EventRepositoryImpl()
): ViewModel() {

    fun getEvents(): LiveData<List<Event>> {
        return repository.getEvents()
    }
}