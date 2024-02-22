package com.sicredi.teste.centraldeeventos.presenter.ui

import com.sicredi.teste.centraldeeventos.data.model.Event

interface OnEventClick {

    fun onClick(event: Event)
}