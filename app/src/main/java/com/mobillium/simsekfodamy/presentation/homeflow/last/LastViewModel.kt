package com.mobillium.simsekfodamy.presentation.homeflow.last

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
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
class LastViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository

) : BaseViewModel() {

    val recipes = MutableLiveData<PagingData<Recipe>>()

    init {
        lastData()
    }

    fun lastData() {
        viewModelScope.launch {

            recipeRepository.getLastAddedRecipes().cachedIn(viewModelScope).collect {
                recipes.value = it
            }
        }
    }
}
