package com.example.finalproject.ui.darkmode

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject.R
import com.example.finalproject.preference.DataPreferences
import com.example.finalproject.remote.ViewModelFactory

import com.google.android.material.switchmaterial.SwitchMaterial
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DarkThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_darktheme)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val pref= DataPreferences.getInstance(dataStore)
        val viewModel= ViewModelProvider(this,ViewModelFactory(pref)).get(DarkThemeViewModel::class.java)


        viewModel.getThemeSetting().observe(this,
            {
                    isDarkModeActive:Boolean->
                if (isDarkModeActive){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked=false
                }
            })
        switchTheme.setOnCheckedChangeListener{_:CompoundButton?,isChecked:Boolean->
            viewModel.saveThemeSetting(isChecked)
        }
    }
}
