package com.sicredi.teste.centraldeeventos.data

import androidx.lifecycle.LiveData
import com.sicredi.teste.centraldeeventos.data.model.Event

interface EventRepository {

    fun getEvents(): LiveData<List<Event>>
}