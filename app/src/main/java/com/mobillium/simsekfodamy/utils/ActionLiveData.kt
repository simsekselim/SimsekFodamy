package com.mobillium.simsekfodamy.utils

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

class ActionLiveData : MutableLiveData<Unit>() {
    @MainThread
    fun call() {
        value = Unit
    }
}