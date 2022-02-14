package com.mobillium.simsekfodamy.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.model.Category
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.CategoryPagingFactory
import com.mobillium.simsekfodamy.utils.Constants.LOGGED_OUT
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
        sendRequest(
            request = {
                Pager(
                    config = pageConfig,
                    pagingSourceFactory = { CategoryPagingFactory(recipeRepository) }
                ).flow
            },
            success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect {
                        category.value = it
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
