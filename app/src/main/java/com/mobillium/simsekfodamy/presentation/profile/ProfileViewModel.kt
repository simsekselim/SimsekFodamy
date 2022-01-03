package com.mobillium.simsekfodamy.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.User
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import com.mobillium.simsekfodamy.utils.Constants
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val userRepository: UserRepository,
    private val preferences: PreferencesManager

) : BaseViewModel() {

    val user = MutableLiveData<User>()
    val navigateLogin = ActionLiveData()
    init {
        fetchUser()
    }

    fun fetchUser() {
        viewModelScope.launch {
            val userId = preferences.getUser()
            when (val response = userRepository.getUser(userId)) {
                is Result.Success -> {
                    user.value = response.response
                }
            }
        }
    }
    fun logout() {

        viewModelScope.launch {
            if (preferences.isLogin()) {
                when (userRepository.logout()) {
                    is Result.Success -> showMessage(LOGGED_OUT)

                    is Result.Error -> println("Error")
                }
            } else {
                navigateLogin.call()
            }
        }
    }
}
