package com.mobillium.simsekfodamy.utils

import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mobillium.simsekfodamy.R

fun Fragment.snackbar(message: String) {
    this.let { view ->
        Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()

    }
}
