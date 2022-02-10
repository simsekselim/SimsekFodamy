package com.mobillium.simsekfodamy.base

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

sealed class BaseViewEvent {
    data class NavigateTo(val directions: NavDirections) : BaseViewEvent()
    object NavigateBack : BaseViewEvent()
    data class ShowMessage(val message: Any) : BaseViewEvent()
    data class ShowLoading(val isShow: Boolean): BaseViewEvent()
    data class Extras(val key: String,val value: Any) : BaseViewEvent()

}
