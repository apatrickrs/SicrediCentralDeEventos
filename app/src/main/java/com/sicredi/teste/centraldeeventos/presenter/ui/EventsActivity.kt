package com.sicredi.teste.centraldeeventos.presenter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sicredi.teste.centraldeeventos.databinding.ActivityMainBinding
import com.sicredi.teste.centraldeeventos.domain.EventsViewModel

class EventsActivity : AppCompatActivity() {

    private lateinit var view: ActivityMainBinding
    private lateinit var events: EventsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        events = ViewModelProvider(this)[EventsViewModel::class.java]
        requestEvents()
    }

    private fun requestEvents() {
        events.getEvents().observe(this) {
            if (it.isNotEmpty()) {
                println(it[0].title)
            } else {
                println("Erro ao buscar EVENTOS")
            }
        }
    }
}