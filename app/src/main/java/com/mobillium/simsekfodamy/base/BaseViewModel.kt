package com.mobillium.simsekfodamy.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.mobillium.data.exceptions.Authentication
import com.mobillium.data.exceptions.BaseException
import com.mobillium.data.exceptions.GettingEmptyListItem
import com.mobillium.data.exceptions.SimpleHttpException
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.utils.FetchExtras
import com.mobillium.simsekfodamy.utils.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

abstract class BaseViewModel : ViewModel(), FetchExtras {

    @CallSuper
    override fun fetchExtras(extras: Bundle) {
    }

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> get() = _loading


    val baseEvent = SingleLiveEvent<BaseViewEvent>()

    fun navigate(directions: NavDirections) {
        baseEvent.value = BaseViewEvent.NavigateTo(directions)
    }

    fun popBackStack() {
        baseEvent.value = BaseViewEvent.NavigateBack
    }

    fun setExtras(key: String, value: Any) {
        baseEvent.value = BaseViewEvent.Extras(key, value)
    }

    fun showMessage(message: String) {
        baseEvent.value = BaseViewEvent.ShowMessage(message)
    }

    fun showMessage(@StringRes message: Int) {
        baseEvent.value = BaseViewEvent.ShowMessage(message)
    }


    private fun showDialog() {
        _loading.value = true
    }

    private fun dismissDialog() {
        _loading.value = false
    }

    fun <T : Any?> sendRequest(
        loading: Boolean = true,
        request: suspend () -> T,
        success: ((T) -> Unit)? = null,
        error: ((Exception) -> Unit)? = null
    ): Job {
        return viewModelScope.launch {
            if (loading) {
                showDialog()
            }
            try {
                val result = request.invoke()
                success?.invoke(result)
                dismissDialog()
            } catch (exception: Exception) {
                dismissDialog()
                if (error != null) {
                    error.invoke(exception)
                } else {
                    handleException(exception)
                }
            }

        }
    }

    private fun handleException(ex: Exception) {
        when (ex) {
            is Authentication -> showMessage(R.string.try_again)
            is IOException -> showMessage(R.string.check_internet)
            is GettingEmptyListItem -> showMessage(R.string.no_comment)
            is SimpleHttpException -> ex.friendlyMessage?.let { showMessage(it) }
            is BaseException -> showMessage(ex.exMessage)
        }
    }
}
