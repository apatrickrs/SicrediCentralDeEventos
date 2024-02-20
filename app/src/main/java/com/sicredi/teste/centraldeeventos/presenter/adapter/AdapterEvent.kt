package com.sicredi.teste.centraldeeventos.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import com.sicredi.teste.centraldeeventos.R
import com.sicredi.teste.centraldeeventos.data.model.Event
import com.sicredi.teste.centraldeeventos.presenter.Component
import com.sicredi.teste.centraldeeventos.presenter.OnEventClick
import com.squareup.picasso.Picasso

class AdapterEvent(

    private val events: List<Event>,
    private val onEventClick: OnEventClick

): RecyclerView.Adapter<AdapterEvent.SliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_event,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.configEvent(events[position], onEventClick)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val eventView: ConstraintLayout
        private val imageEvent: RoundedImageView
        private val titleEvent: TextView
        private val dateEvent: TextView

        init {
            eventView = itemView.findViewById(R.id.eventView)
            imageEvent = itemView.findViewById(R.id.imageEvent)
            titleEvent = itemView.findViewById(R.id.adapterTitleEvent)
            dateEvent = itemView.findViewById(R.id.adapterDateEvent)
        }

        fun configEvent(event: Event, onEventClick: OnEventClick) {
            titleEvent.text = event.title
            dateEvent.text = Component.formatDate(event.date)
            Picasso.get().load(event.image).into(imageEvent)

            eventView.setOnClickListener {
                onEventClick.onClick(event)
            }
        }
    }
}