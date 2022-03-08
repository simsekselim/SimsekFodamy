package com.mobillium.simsekfodamy.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.model.Category
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val user: UserRepository,
    private val preferences: PreferencesManager
) : BaseViewModel() {

    private val _category: MutableLiveData<PagingData<Category>> = MutableLiveData()
    val category: LiveData<PagingData<Category>> get() = _category

    init {
        getRecipeCategory()
    }

    private fun getRecipeCategory() = viewModelScope.launch {
        sendRequest(
            request = {
                recipeRepository.getCategoriesPaging()
            },
            success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect {
                        _category.value = it
                    }
                }
            }
        )
    }

    fun logout() = viewModelScope.launch {
        if (preferences.isLogin()) {
            sendRequest(
                loading = true,
                request = { user.logout() },
                success = {
                    showMessage(LOGGED_OUT)
                }
            )
        } else {
            navigate(FavoritesFragmentDirections.actionFavoritesFragmentToFragmentLogin())
        }
    }

    fun seeAll(categoryId: Int) {
        navigate(FavoritesFragmentDirections.actionFavoritesFragmentToCategoryFragment(categoryId))
    }

    fun onRecipeClick(recipeId: Int) {
        navigate(FavoritesFragmentDirections.actionFavoritesFragmentToRecipeDetailFragment(recipeId))
    }

    companion object {
        private val pageConfig = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        )
    }
}
