package com.example.finalproject.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

class DataPrefrences constructor(private val dataStore: DataStore<Preferences>) {
    companion object{
    @Volatile
    private var INSTANCE:DataPrefrences?=null

    fun getInstance(dataStore: DataStore<Preferences>):DataPrefrences {
        return INSTANCE ?: synchronized(this) {
            val instance = DataPrefrences(dataStore)
            INSTANCE = instance
            instance
        }
    }
    }
}