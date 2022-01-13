package com.mobillium.simsekfodamy.presentation.loginflow.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.handleHttpException
import com.mobillium.simsekfodamy.repository.UserRepository
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

    val username = MutableLiveData(USERNAME)
    val password = MutableLiveData(PASSWORD)
    val validation = MediatorLiveData<Boolean>().apply {
        addSource(username) { value = validateUsername() }
        addSource(password) { value = validatePassword() }
    }

    fun onClickLogin() = viewModelScope.launch {

        when (
            val response =
                repository.login(username.value.toString(), password.value.toString())
        ) {
            is Result.Success -> {
                navigate(LoginFragmentDirections.actionFragmentLoginToHome())
            }
            is Result.Error -> {
                response.exception.handleHttpException()
            }
        }
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
    private fun validateUsername(): Boolean {
        return username.value!!.isNotBlank()
    }

    private fun validatePassword(): Boolean {
        return password.value!!.isNotBlank()
    }
    companion object {
        const val USERNAME = "salihaga"
        const val PASSWORD = "fodamy48+"
    }
}
