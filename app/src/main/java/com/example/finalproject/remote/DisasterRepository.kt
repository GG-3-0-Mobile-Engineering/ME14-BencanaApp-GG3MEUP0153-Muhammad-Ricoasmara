package com.example.finalproject.remote

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.finalproject.api.ApiConfig
import com.example.finalproject.api.ApiResponse
import com.example.finalproject.api.ApiService
import com.example.finalproject.api.GeometriesItem
import com.mapbox.maps.extension.style.expressions.dsl.generated.properties
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisasterRepository(private val apiService: ApiService) {
    fun getDisaster(): LiveData<Result<ApiResponse>> = liveData {
        emit(Result.Loading)
        try {
            apiService

        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
    fun getRecentDisasters(callback: (List<GeometriesItem>?) -> Unit) {
        ApiConfig.getApiService().getRecent(604800).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val recentDisaster = response.body()?.result?.objects?.output?.geometries
                callback(recentDisaster)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

}