package com.example.healthapp.remote

import com.example.healthapp.data.remote.HealthTipApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object HealthTipApiModule {

    // 真正的 API baseUrl
    private const val BASE_URL = "https://odphp.health.gov/myhealthfinder/api/v3/"

    fun provideApiService(): HealthTipApiService {
        val gson = GsonBuilder()
            .registerTypeAdapter(HealthTipResult::class.java, ItemsAdapter())
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(HealthTipApiService::class.java)
    }
}



// 对应你的 API: https://odphp.health.gov/myhealthfinder/api/v3/itemlist.json?Type=topic
//https://odphp.health.gov/myhealthfinder/api/v3/