package com.mobillium.simsekfodamy.base

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

    fun showMessage(message: String) = viewModelScope.launch {
        baseEvent.postValue(BaseViewEvent.ShowMessage(message))
    }
}
