package com.mobillium.simsekfodamy.presentation.loginflow.login

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.domain.model.ApiClient
import com.mobillium.simsekfodamy.domain.model.AuthRepository
import com.mobillium.simsekfodamy.domain.model.LoginRequest
import com.mobillium.simsekfodamy.domain.model.SessionManager
import com.mobillium.simsekfodamy.presentation.loginflow.forgetpassword.FragmentForgetPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel() {


    val username = MutableLiveData<String>()
    val password = MutableLiveData<String>()





}




