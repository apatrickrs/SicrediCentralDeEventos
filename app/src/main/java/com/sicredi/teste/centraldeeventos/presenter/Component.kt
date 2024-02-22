package com.sicredi.teste.centraldeeventos.presenter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.Button
import android.widget.TextView
import com.sicredi.teste.centraldeeventos.R
import com.sicredi.teste.centraldeeventos.data.model.Event
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Component {

    fun startBannerEventDetail(context: Context, event: Event, onCheckInClick: BannerEventsClick) {
        val banner = Dialog(context)
        banner.setContentView(R.layout.event_details_banner)

        val bannerTitleEvent: TextView = banner.findViewById(R.id.bannerTitleEvent)
        val bannerDateEvent: TextView = banner.findViewById(R.id.bannerDateEvent)
        val bannerDescriptionEvent: TextView = banner.findViewById(R.id.bannerDescriptionEvent)
        val bannerPriceEvent: TextView = banner.findViewById(R.id.bannerPriceEvent)

        val btnClose: TextView = banner.findViewById(R.id.icCloseBanner)
        val btnCheckIn: Button = banner.findViewById(R.id.btnCheckIn)
        val btnShareEvent: Button = banner.findViewById(R.id.btnShareEvent)

        bannerTitleEvent.text = event.title
        bannerDateEvent.text = formatDate(event.date)
        bannerDescriptionEvent.text = event.description
        bannerPriceEvent.text = NumberFormat
            .getCurrencyInstance(Locale("pt", "BR"))
            .format(event.price)

        btnClose.setOnClickListener {
            banner.dismiss()
        }

        btnCheckIn.setOnClickListener {
            onCheckInClick.onCheckInClick()
        }

        btnShareEvent.setOnClickListener {
            val contentToShare = event.title + "\n" + formatDate(event.date)

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, contentToShare)
            context.startActivity(Intent.createChooser(intent, ""))
        }

        banner.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        banner.setCancelable(false)
        banner.show()
    }

    @SuppressLint("SimpleDateFormat")
    fun formatDate(date: Long): String {
        val dateInstance = Date(date)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        return dateFormat.format(dateInstance)
    }
}