package com.example.finalproject

import com.example.finalproject.api.ApiResponse
import com.example.finalproject.api.Properties
import com.example.finalproject.api.Result
import com.google.gson.Gson
import android.util.Log

val serializesData=ApiResponse(
    statusCode=200,
    result = Result(
        type="Topology",
        arcs = null,
        bbox = listOf(
            Properties(
                text = "saat lapangan Bola menjadi blablabal"
            )

        )
    )
)

val test= Gson().toJson(serializesData)

