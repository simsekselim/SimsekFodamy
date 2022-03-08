package com.mobillium.simsekfodamy.presentation.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.model.User
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val preferences: PreferencesManager

) : BaseViewModel() {

    val user = MutableLiveData<User>()

    init {
        fetchUser()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            val userId = preferences.getUser()
            sendRequest(
                request = { userRepository.getUser(userId) },
                success = {
                    user.value = it
                }
            )
        }
    }

    fun logout() = viewModelScope.launch {
        if (preferences.isLogin()) {
            sendRequest(
                loading = false,
                request = { userRepository.logout() },
                success = {
                    showMessage(it.message)
                }
            )
        } else {
            navigate(ProfileFragmentDirections.actionProfileFragmentToFragmentLogin())
        }
    }
}
