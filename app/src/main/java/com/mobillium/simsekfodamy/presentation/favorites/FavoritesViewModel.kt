package com.mobillium.simsekfodamy.presentation.favorites

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.repository.RecipeRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : BaseViewModel() {


    private val categoryFlow = recipeRepository.getRecipeCategories().cachedIn(viewModelScope)

    val categories = categoryFlow.asLiveData()
}