package com.mobillium.simsekfodamy.presentation.loginflow.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseFragment
import com.mobillium.simsekfodamy.domain.model.ApiClient
import com.mobillium.simsekfodamy.domain.model.LoginRequest
import com.mobillium.simsekfodamy.domain.model.LoginResponse
import com.mobillium.simsekfodamy.domain.model.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
@AndroidEntryPoint
class FragmentLogin : Fragment() {

    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val register : TextView = view.findViewById(R.id.register)
        register.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.fragmentRegister)
        }

        val forget : TextView = view.findViewById(R.id.forget)
        forget.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.fragmentForgetPassword)
        }



        apiClient = ApiClient()
        sessionManager = SessionManager(requireContext())

        val login : Button = view.findViewById(R.id.login)
        val userName : EditText = view.findViewById(R.id.userName)
        val userPassword : EditText = view.findViewById(R.id.userPassword)



        login.setOnClickListener {
            apiClient.getApiService().login(LoginRequest(username = "${userName.text}", password = "${userPassword.text}"))
                .enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        // Error logging in
                    }

                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        val loginResponse = response.body()

                        if (loginResponse?.user != null) {
                            sessionManager.saveAuthToken(loginResponse.token)
                        } else {
                            // Error logging in
                        }
                    }
                })



        }





    }




}


