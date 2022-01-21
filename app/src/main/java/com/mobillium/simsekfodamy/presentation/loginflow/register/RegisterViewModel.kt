package com.mobillium.simsekfodamy.presentation.loginflow.register

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
class RegisterViewModel
@Inject
constructor(private val repository: UserRepository) : BaseViewModel() {

    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val validation = MediatorLiveData<Boolean>().apply {
        addSource(username) { value = validate() }
        addSource(password) { value = validate() }
        addSource(email) { value = validate() }
    }

    fun onClickRegister() = viewModelScope.launch {
        when (
            val response =
                repository.register(
                    username.value.toString(),
                    email.value.toString(),
                    password.value.toString()
                )
        ) {
            is Result.Success -> {
                navigate(RegisterFragmentDirections.actionFragmentRegisterToFragmentLogin())
            }
            is Result.Error -> {
                response.exception.handleHttpException()
            }
        }
    }

    private fun validate(): Boolean {
        return !username.value.isNullOrBlank() && !password.value.isNullOrBlank() && password.value!!.length > 6 && !email.value.isNullOrBlank()
    }

    fun toLogin() {
        navigate(RegisterFragmentDirections.actionFragmentRegisterToFragmentLogin())
    }
}
