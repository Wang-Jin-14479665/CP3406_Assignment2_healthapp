package com.example.healthapp.remote

import com.google.gson.annotations.SerializedName

data class HealthTipResult(
    @SerializedName("Items")
    val items: List<HealthTipResponseItem>
)