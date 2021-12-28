package com.mobillium.simsekfodamy.base

import androidx.navigation.NavDirections

sealed class BaseViewEvent {
    data class NavigateTo(val directions: NavDirections) : BaseViewEvent()
    object NavigateBack : BaseViewEvent()
    data class ShowLoading(val isShow: Boolean) : BaseViewEvent()

}