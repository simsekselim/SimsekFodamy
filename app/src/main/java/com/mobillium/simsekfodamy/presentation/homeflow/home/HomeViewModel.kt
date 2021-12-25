package com.mobillium.simsekfodamy.presentation.homeflow.home

import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import com.mobillium.simsekfodamy.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.mobillium.simsekfodamy.utils.Result
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository,
    private val user : UserRepository,
    private val preferences : PreferencesManager

    ) : BaseViewModel() {

    val navigateLogin = ActionLiveData()



    fun logout(){

        viewModelScope.launch {
            if(preferences.isLogin()){
                when(user.logout()){
                    is Result.Success -> println("Logged Out")

                    is Result.Error -> println("Error")
                }

            }else{
                navigateLogin.call()

            }

        }
    }
    suspend fun getToken() = preferences.token



}















