package com.mobillium.simsekfodamy.presentation.loginflow.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mobillium.simsekfodamy.R

class FragmentLogin : Fragment() {

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

    }
}