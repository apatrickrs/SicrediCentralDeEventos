package com.sicredi.teste.centraldeeventos.presenter

import com.sicredi.teste.centraldeeventos.data.model.Event

interface OnEventClick {

    fun onClick(event: Event)
}