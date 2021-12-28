package com.mobillium.simsekfodamy.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mobillium.simsekfodamy.utils.SingleLiveEvent

import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val baseEvent = SingleLiveEvent<BaseViewEvent>()


    fun navigate(directions: NavDirections) = viewModelScope.launch {
        baseEvent.postValue(BaseViewEvent.NavigateTo(directions))
    }

    fun popBackStack() {
        viewModelScope.launch {
            baseEvent.postValue(BaseViewEvent.NavigateBack)
        }
    }

    fun showDialog() = viewModelScope.launch {
        baseEvent.postValue(BaseViewEvent.ShowLoading(true))
    }

}


