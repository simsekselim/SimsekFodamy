package com.mobillium.simsekfodamy.presentation.loginflow.register

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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

    fun onClickRegister() {
        val username = username.value.toString()
        val email = email.value.toString()
        val password = password.value.toString()
        if(validation.value == true)
        sendRequest(
            request = {repository.register(username,email,password)},
            success = {
                showMessage(SUCCESS)
                toLogin()

            }
        )

    }



    private fun validate(): Boolean {
        return !username.value.isNullOrBlank() && !password.value.isNullOrBlank() && password.value!!.length > 6 && !email.value.isNullOrBlank()
    }

    fun toLogin() {
        navigate(RegisterFragmentDirections.actionFragmentRegisterToFragmentLogin())
    }

    companion object{
        const val SUCCESS = "Kayıt İşlemi Tamamlanmıştır."
    }
}
