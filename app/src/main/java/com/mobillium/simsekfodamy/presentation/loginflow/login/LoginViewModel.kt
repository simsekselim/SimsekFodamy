package com.mobillium.simsekfodamy.presentation.loginflow.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val repository: UserRepository
) : BaseViewModel() {

    val username = MutableLiveData(USERNAME)
    val password = MutableLiveData(PASSWORD)
    val validation = MediatorLiveData<Boolean>().apply {
        addSource(username) { value = validate() }
        addSource(password) { value = validate() }
    }

    fun onClickLogin() {
        val username = username.value.toString()
        val password = password.value.toString()

        if (validation.value == true) {
            sendRequest(
                loading = false,
                request = { repository.login(username, password) },
                success = {
                    toHome()
                }
            )
        }
    }

    private fun toHome() {
        navigate(LoginFragmentDirections.actionFragmentLoginToHome())
    }

    fun toForget() {
        viewModelScope.launch {
            navigate(LoginFragmentDirections.actionFragmentLoginToFragmentForgetPassword())
        }
    }

    fun toRegister() {
        viewModelScope.launch {
            navigate(LoginFragmentDirections.actionFragmentLoginToFragmentRegister())
        }
    }
    private fun validate(): Boolean {
        return !username.value.isNullOrBlank() && !password.value.isNullOrBlank() && password.value!!.length > 6
    }

    companion object {
        const val USERNAME = "salihaga"
        const val PASSWORD = "fodamy48+"
    }
}
