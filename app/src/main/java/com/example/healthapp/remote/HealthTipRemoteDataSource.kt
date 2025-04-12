package com.example.healthapp.remote

import android.util.Log
import com.example.healthapp.data.HealthTipEntity
import com.example.healthapp.data.remote.HealthTipApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HealthTipRemoteDataSource(
    private val api: HealthTipApiService
) {
    suspend fun fetchHealthTips(): List<HealthTipEntity> = withContext(Dispatchers.IO) {
        try {
            val response = api.getHealthTips()
            if (response.isSuccessful) {
                val body = response.body()
                Log.d("HealthRemoteDS", "API call success, body: $body")
                Log.d("HealthRemoteDS", "API items: ${body?.result?.items}")

                val items = body?.result?.items.orEmpty()
                Log.d("HealthRemoteDS", "items size: ${items.size}")

                items.map { item ->
                    HealthTipEntity(
                        title = item.title ?: "No Title",
                        description = "ID: ${item.id ?: "null"}"
                    )
                }

            } else {
                Log.e("HealthRemoteDS", "API call failed: ${response.code()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("HealthRemoteDS", "Exception in fetchHealthTips: ${e.message}")
            emptyList()
        }
    }
}
