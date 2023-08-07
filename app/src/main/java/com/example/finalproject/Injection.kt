package com.example.finalproject

import android.content.Context
import com.example.finalproject.api.ApiConfig
import com.example.finalproject.preference.DataPreferences
import com.example.finalproject.remote.DisasterRepository

object Injection {


    fun repositoryInjection(context: DataPreferences): DisasterRepository {
        val apiService= ApiConfig.getApiService()
        return DisasterRepository(apiService)
    }
}