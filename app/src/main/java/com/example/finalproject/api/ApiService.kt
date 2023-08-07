package com.example.finalproject.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("reports/archive")
   suspend fun getListUsers(
        @Query("start")start: String,
        @Query("end")end:String
    ): Call<ApiResponse>

   @GET("report")
     fun getListDisaster():Call<ApiResponse>

    @GET("reports?")
    fun getRecent(@Query("timeperiod") timeperiod: Int):Call<ApiResponse>

    @GET("reports?")
    fun getAllDisaster(
        @Query("timeperiod") timeperiod: Int? = 172800,
        @Query("admin") admin: String? = null,
        @Query("disaster") disaster: String? = null
    ):Call<ApiResponse>
}