package com.example.finalproject.ui.darkmode

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.finalproject.preference.DataPreferences
import kotlinx.coroutines.launch


class DarkThemeViewModel(private val pref:DataPreferences): ViewModel() {
    fun getThemeSetting(): LiveData<Boolean>{
        return pref.getTheme().asLiveData()
    }
    fun saveThemeSetting(isDarkModeActive:Boolean){
        viewModelScope.launch {
            pref.saveTheme(isDarkModeActive)
        }
    }
}