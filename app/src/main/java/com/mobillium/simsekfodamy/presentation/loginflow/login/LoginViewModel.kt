package com.mobillium.simsekfodamy.presentation.loginflow.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    val username = MutableLiveData("salihaga")
    val password = MutableLiveData("fodamy48+")
    val navigateMain = ActionLiveData()

    fun onClickLogin() = viewModelScope.launch {
        if (username.value.isNullOrBlank() || password.value.isNullOrBlank()) {

            return@launch
        }
        when (
            val response =
                repository.login(username.value.toString(), password.value.toString())
        ) {
            is Result.Success -> {


                println("Success")
                navigateMain.call()
            }
            is Result.Error -> {

                println("Failure")
            }
        }
    }
}
