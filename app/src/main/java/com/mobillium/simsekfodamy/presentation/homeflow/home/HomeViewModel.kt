package com.mobillium.simsekfodamy.presentation.homeflow.home

import androidx.lifecycle.viewModelScope
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT
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
                sendRequest(
                    request = {user.logout()},
                    success = {
                        showMessage(LOGGED_OUT)
                    }
                )
            } else {
                navigate(HomeFragmentDirections.actionHomeFragmentToFragmentLogin())
            }
        }
    }
}
