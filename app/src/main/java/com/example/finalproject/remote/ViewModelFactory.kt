package com.example.finalproject.remote

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.Injection
import com.example.finalproject.preference.DataPreferences
import com.example.finalproject.ui.darkmode.DarkThemeViewModel
import com.example.finalproject.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor (private val pref: DataPreferences) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DarkThemeViewModel::class.java) -> {
                DarkThemeViewModel(pref) as T
            }

            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(Injection.repositoryInjection(pref)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
