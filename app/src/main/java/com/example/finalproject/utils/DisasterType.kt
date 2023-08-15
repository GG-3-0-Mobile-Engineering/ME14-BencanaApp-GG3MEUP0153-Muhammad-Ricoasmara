package com.example.finalproject.utils

import com.example.finalproject.R

enum class DisasterType (val idDisaster:String,val value:String, val colorDisaster:Int) {
    FLOOD("Banjir","flood", R.color.Flood),
    EARTHQUAKE("Gempa Bumi", "earthquake", R.color.Earthquake),
    FIRE("Kebakaran Hutan", "fire", R.color.Fire),
    HAZE("Kabut Asap", "haze", R.color.Haze),
    WIND("Angin Kencang", "wind", R.color.Wind),
    VOLCANO("Gunung Api", "volcano", R.color.Volcano)

}