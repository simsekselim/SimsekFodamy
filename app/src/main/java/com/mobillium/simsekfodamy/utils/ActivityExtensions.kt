package com.mobillium.simsekfodamy.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat



    fun AppCompatActivity.hideIme() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller?.hide(WindowInsetsCompat.Type.ime())
    }

    fun AppCompatActivity.showIme() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller?.show(WindowInsetsCompat.Type.ime())
    }
