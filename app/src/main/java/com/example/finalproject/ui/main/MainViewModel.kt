package com.example.finalproject.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.api.ApiConfig
import com.example.finalproject.api.ApiResponse
import com.example.finalproject.api.GeometriesItem
import com.example.finalproject.remote.DisasterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel(){
    private val _disasterList=MutableLiveData<List<GeometriesItem>?>()
    val disasterList: MutableLiveData<List<GeometriesItem>?> get() = _disasterList

    fun getListDisaster() {
        ApiConfig.instance.getRecent(604800).enqueue(object :Callback<ApiResponse>{
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                _disasterList.value= response.body()?.result?.objects?.output?.geometries as List<GeometriesItem>?
                Log.i("CHECK_RESPONSE", "${response.body()?.statusCode}")

            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}
