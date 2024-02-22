package com.sicredi.teste.centraldeeventos.presenter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sicredi.teste.centraldeeventos.presenter.BannerEventsClick
import com.sicredi.teste.centraldeeventos.R
import com.sicredi.teste.centraldeeventos.data.model.CheckInEvent
import com.sicredi.teste.centraldeeventos.data.model.Event
import com.sicredi.teste.centraldeeventos.databinding.ActivityMainBinding
import com.sicredi.teste.centraldeeventos.domain.EventsViewModel
import com.sicredi.teste.centraldeeventos.presenter.Component
import com.sicredi.teste.centraldeeventos.presenter.OnEventClick
import com.sicredi.teste.centraldeeventos.presenter.adapter.AdapterEvent

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

    private fun setNextEvents(events: List<Event>) {
        view.rvNextEvents.layoutManager = LinearLayoutManager(this)
        view.rvNextEvents.setHasFixedSize(true)
        view.rvNextEvents.adapter = AdapterEvent(events, object : OnEventClick {
            override fun onClick(event: Event) {
                requestEventById(event.id.toInt())
            }
        })
    }

    private fun requestEvents() {
        events.getEvents().observe(this) {
            if (it.isNotEmpty()) {
                setNextEvents(it)
            } else {
                showMessageStatus(getString(R.string.error_find_events))
            }
        }
    }

    private fun requestEventById(id: Int) {
        events.getEventById(id).observe(this) {
            if (it != null) {
                Component.startBannerEventDetail(this, it, object : BannerEventsClick {
                    override fun onCheckInClick() {
                        requestDoCheckinOnEvent(it.id)
                    }
                })
            } else {
                showMessageStatus(getString(R.string.error_find_event))
            }
        }
    }

    private fun requestDoCheckinOnEvent(id: String) {
        val checkinData = CheckInEvent(
            id,
            "Andrew",
            "andrew_patrick@gmail.com"
        )

        events.doCheckinOnEvent(checkinData).observe(this) {
            if (it.isNotEmpty()) {
                showMessageStatus(getString(R.string.success_check_in) + it)
            } else {
                showMessageStatus(getString(R.string.error_check_in))
            }
        }
    }

    private fun showMessageStatus(info: String) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }
}