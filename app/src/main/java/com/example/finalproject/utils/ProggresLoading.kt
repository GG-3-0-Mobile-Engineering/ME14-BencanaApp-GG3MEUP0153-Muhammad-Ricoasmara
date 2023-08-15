package com.example.finalproject.utils

import android.view.View

class ProggresLoading {
    fun viewLoading(isLoading: Boolean, progressBar: View){
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}