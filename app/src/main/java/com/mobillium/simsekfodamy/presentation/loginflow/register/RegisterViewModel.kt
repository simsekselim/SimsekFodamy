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

    val username = MutableLiveData("")
    val password = MutableLiveData("")
    val email = MutableLiveData("")
    val validation = MediatorLiveData<Boolean>().apply {
        addSource(username) { value = validateUsername() }
        addSource(password) { value = validatePassword() }
        addSource(email) { value = validateMail() }
    }

    fun onClickRegister() = viewModelScope.launch {
        if (validation.value == true) {
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
    }

    private fun validateUsername(): Boolean {
        return username.value!!.isNotBlank()
    }

    private fun validatePassword(): Boolean {
        return return password.value!!.length > 6
    }

    private fun validateMail(): Boolean {
        return email.value!!.isNotBlank()
    }
    fun toLogin() {
        navigate(RegisterFragmentDirections.actionFragmentRegisterToFragmentLogin())
    }
}


