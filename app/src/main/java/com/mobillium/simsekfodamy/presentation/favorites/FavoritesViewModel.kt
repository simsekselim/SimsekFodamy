package com.mobillium.simsekfodamy.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Category
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT_ERROR
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val user: UserRepository,
    private val preferences: PreferencesManager
) : BaseViewModel() {

    val category = MutableLiveData<PagingData<Category>>()


    init {
        getRecipeCategory()
    }

    private fun getRecipeCategory() = viewModelScope.launch {
        recipeRepository.getRecipeCategories().cachedIn(viewModelScope).collect {
            category.value = it
        }
    }

    fun logout() = viewModelScope.launch {
        if (preferences.isLogin()) {
            when (user.logout()) {
                is Result.Success -> showMessage(LOGGED_OUT)

                is Result.Error -> showMessage(LOGGED_OUT_ERROR)
            }
        } else {
            navigate(FavoritesFragmentDirections.actionFavoritesFragmentToFragmentLogin())
        }
    }

    fun seeAll(categoryId : Int){
        navigate(FavoritesFragmentDirections.actionFavoritesFragmentToCategoryFragment(categoryId))
    }
    fun onRecipeClick(recipeId :Int){
        navigate(FavoritesFragmentDirections.actionFavoritesFragmentToRecipeDetailFragment(recipeId))
    }
}
