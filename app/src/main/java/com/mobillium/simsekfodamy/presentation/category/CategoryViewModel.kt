package com.mobillium.simsekfodamy.presentation.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: RecipeRepository,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    val categoryName = stateHandle.get<String>("name")
    private val categoryId = stateHandle.get<Int>("id") ?: 0

    private val categoryRecipesFlow =
        repository.getCategoryRecipes(categoryId).cachedIn(viewModelScope)
    val recipes = categoryRecipesFlow.asLiveData()


}
