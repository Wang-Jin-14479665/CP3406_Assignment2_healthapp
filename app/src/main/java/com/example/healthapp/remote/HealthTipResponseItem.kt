package com.example.healthapp.remote

import com.google.gson.annotations.SerializedName

data class HealthTipResponseItem(
    @SerializedName("Id")
    val id: String?,

    @SerializedName("Title")
    val title: String?,
)
