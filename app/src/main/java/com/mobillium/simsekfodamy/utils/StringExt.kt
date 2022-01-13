package com.mobillium.simsekfodamy.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.snackbar(message: String) {
    this.let { view ->
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}
