package com.mobillium.simsekfodamy.presentation.loginflow.register

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
class RegisterViewModel
@Inject
constructor(private val repository: UserRepository) : BaseViewModel() {


    val username = MutableLiveData("")
    val password = MutableLiveData("")
    val email = MutableLiveData("")
    val navigateLogin = ActionLiveData()

    fun onClickRegister() = viewModelScope.launch {

        when (
            val response =
                repository.register(username.value.toString(),email.value.toString(), password.value.toString())
        ) {
            is Result.Success -> {

                println("Success")
                navigateLogin.call()


            }
            is Result.Error -> {

                println("Failure")
            }
        }

    }


}
