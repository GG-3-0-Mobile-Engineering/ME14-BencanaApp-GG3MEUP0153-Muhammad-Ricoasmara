package com.example.finalproject.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.finalproject.api.ApiResponse
import com.example.finalproject.api.ApiService

class DisasterRepository(
    private val apiService: ApiService
    ){
    fun getList(): LiveData<Result<ApiResponse>> = liveData{

    }
}