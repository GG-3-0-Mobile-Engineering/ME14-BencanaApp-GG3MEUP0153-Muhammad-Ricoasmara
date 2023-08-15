package com.example.finalproject.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.adapter.Disasteradapater
import com.example.finalproject.api.ApiConfig
import com.example.finalproject.api.ApiResponse
import com.example.finalproject.api.GeometriesItem
import com.example.finalproject.preference.DataPreferences
import com.example.finalproject.remote.DisasterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainViewModel(private val repository: DisasterRepository) : ViewModel() {
    private val _disasterList = MutableLiveData<List<GeometriesItem>?>()
    val disasterList: MutableLiveData<List<GeometriesItem>?> get() = _disasterList

    private var _apiResponse = MutableLiveData<ApiResponse>()
    val apiResponse: LiveData<ApiResponse>
        get() = _apiResponse

   fun getRecentReport(){
       viewModelScope.launch {
           repository.getRecentDisasters { recentDisaster->
               _disasterList.value=recentDisaster
           }
       }
   }
  fun getDisasterTypeModel(disaterType:String){
      ApiConfig.getApiService().getDisasterType(disaterType).enqueue(object:Callback<ApiResponse>{
          override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if(response.isSuccessful){
                response.body()?.result?.objects?.output?.geometries
            }else{

            }
          }

          override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
              TODO("Not yet implemented")
          }

      })
  }
}
