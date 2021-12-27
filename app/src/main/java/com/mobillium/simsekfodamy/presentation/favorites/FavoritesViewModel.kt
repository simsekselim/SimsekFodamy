package com.mobillium.simsekfodamy.presentation.favorites

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val user: UserRepository,
    private val preferences: PreferencesManager
) : BaseViewModel() {

    val navigateLogin = ActionLiveData()

    private val categoryFlow = recipeRepository.getRecipeCategories().cachedIn(viewModelScope)

    val categories = categoryFlow.asLiveData()

    fun logout() {

        viewModelScope.launch {
            if (preferences.isLogin()) {
                when (user.logout()) {
                    is Result.Success -> println("Logged Out")


                    is Result.Error -> println("Error")
                }

            } else {
                navigateLogin.call()

            }

        }
    }
}