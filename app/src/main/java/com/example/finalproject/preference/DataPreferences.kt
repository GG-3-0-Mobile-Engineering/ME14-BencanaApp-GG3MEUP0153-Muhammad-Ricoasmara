package com.example.finalproject.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow


class DataPreferences constructor(private val dataStore: DataStore<Preferences>) {
    companion object{
    @Volatile
    private var INSTANCE:DataPreferences?=null
    private val themeKey= booleanPreferencesKey("theme_setting")

    fun getInstance(dataStore: DataStore<Preferences>):DataPreferences {
        return INSTANCE ?: synchronized(this) {
            val instance = DataPreferences(dataStore)
            INSTANCE = instance
            instance
        }
    }
    }
    fun getTheme():Flow<Boolean>{
        return dataStore.data.map { preferences->
            preferences[themeKey]?:false
        }
    }
    suspend fun saveTheme(isDarkMode:Boolean){
        dataStore.edit { preferences->
            preferences[themeKey]=isDarkMode
        }
    }
}