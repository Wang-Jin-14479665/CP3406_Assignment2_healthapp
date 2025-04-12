package com.example.healthapp.data.remote


import com.example.healthapp.remote.HealthTipResponse
import retrofit2.Response
import retrofit2.http.GET

// 对应你的 API: https://odphp.health.gov/myhealthfinder/api/v3/itemlist.json?Type=topic
interface HealthTipApiService {
    @GET("itemlist.json?Type=topic")
    suspend fun getHealthTips(): Response<HealthTipResponse>
}