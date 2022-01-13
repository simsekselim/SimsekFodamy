package com.mobillium.simsekfodamy.presentation.category

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: RecipeRepository,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val categoryId = stateHandle.get<Int>(CATEGORY_ID) ?: 0

    val category = MutableLiveData<PagingData<Recipe>>()

    init {
        getRecipes()
    }

    private fun getRecipes() = viewModelScope.launch {
        repository.getCategoryRecipes(categoryId).cachedIn(viewModelScope).collect {
            category.value = it
        }
    }

    fun toDetail(recipe: Int) {
        navigate(CategoryFragmentDirections.actionCategoryFragmentToRecipeDetailFragment(recipe))
    }

    companion object {
        const val CATEGORY_ID = "categoryId"
    }
}


