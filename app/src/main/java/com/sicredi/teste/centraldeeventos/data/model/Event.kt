package com.sicredi.teste.centraldeeventos.data.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id")
    val id: String,

    @SerializedName("people")
    val people: List<Any>,

    @SerializedName("date")
    val date: Long,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("price")
    val price: Double,

    @SerializedName("title")
    val title: String
)
