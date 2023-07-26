package com.example.finalproject.ui.darkmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import com.example.finalproject.R

import com.google.android.material.switchmaterial.SwitchMaterial

class DarkThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_darktheme)

        val switchTheme=findViewById<SwitchMaterial>(R.id.switch_theme)


        switchTheme.setOnCheckedChangeListener{_:CompoundButton?,isChecked:Boolean->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false

            }
        }

    }





}