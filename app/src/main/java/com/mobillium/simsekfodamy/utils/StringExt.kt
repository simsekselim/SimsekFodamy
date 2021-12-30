package com.mobillium.simsekfodamy.utils

import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.mobillium.simsekfodamy.R

fun Fragment.snackbar(message: String) {
    this.let { view ->
        val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.text_action) { snackbar.dismiss() }
        val view = snackbar.view
        val params: FrameLayout.LayoutParams = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        snackbar.setBackgroundTint(Color.RED)
        snackbar.show()
    }
}
