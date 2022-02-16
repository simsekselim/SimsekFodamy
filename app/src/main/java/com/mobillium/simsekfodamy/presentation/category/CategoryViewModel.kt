package com.mobillium.simsekfodamy.presentation.category

import android.os.Bundle
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.RecipePagingFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: RecipeRepository,
) : BaseViewModel() {

    private var categoryId: Int? = null

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        categoryId = CategoryFragmentArgs.fromBundle(extras).categoryId
    }

    val category = MutableLiveData<PagingData<Recipe>>()

    init {
        getRecipes()
    }

    private fun getRecipes() {
        sendRequest(
            request = {
                Pager(
                    config = pageConfig,
                    pagingSourceFactory = {
                        RecipePagingFactory(
                            repository,
                            GET_CATEGORY_RECIPES,
                            categoryId
                        )
                    }
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

    fun toDetail(recipe: Int) {
        navigate(CategoryFragmentDirections.actionCategoryFragmentToRecipeDetailFragment(recipe))
    }

    companion object {
        private val pageConfig = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        )
        const val CATEGORY_ID = "categoryId"
        const val GET_CATEGORY_RECIPES = "GET_CATEGORY_RECIPES"
    }
}
