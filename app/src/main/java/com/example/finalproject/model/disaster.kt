package com.example.finalproject.model

data class disaster(
    val pkey: String,
    val created_at: String,
    val source: String,
    val status: String,
    val image_url: String?,
    val disaster_type: String,
    val url: String?,
    val title: String,
    val text: String
)
