package com.mobillium.simsekfodamy.presentation.homeflow.home

import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT_ERROR
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val user: UserRepository,
    private val preferences: PreferencesManager

) : BaseViewModel() {

    fun logout() {

        viewModelScope.launch {
            if (preferences.isLogin()) {
                when (user.logout()) {
                    is Result.Success -> showMessage(LOGGED_OUT)

                    is Result.Error -> showMessage(LOGGED_OUT_ERROR)
                }
            } else {
                navigate(HomeFragmentDirections.actionHomeFragmentToFragmentLogin())
            }
        }
    }
}
