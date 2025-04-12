package com.example.healthapp.remote

import com.google.gson.annotations.SerializedName

data class HealthTipResponse(
    @SerializedName("Result")
    val result: HealthTipResult
)