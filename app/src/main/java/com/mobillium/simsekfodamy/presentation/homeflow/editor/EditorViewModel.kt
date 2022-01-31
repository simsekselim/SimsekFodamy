package com.mobillium.simsekfodamy.presentation.homeflow.editor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.presentation.homeflow.home.HomeFragmentDirections
import com.mobillium.simsekfodamy.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository

) : BaseViewModel() {

    val recipes = MutableLiveData<PagingData<Recipe>>()

    init {
        editorData()
    }

    private fun editorData() {
    sendRequest(
        request = { recipeRepository.getEditorChoiceRecipes() },
        success = {

        }
    )
        viewModelScope.launch {

            recipeRepository.getEditorChoiceRecipes().cachedIn(viewModelScope).collect {
                recipes.value = it
            }
        }
    }
    fun editor(recipeId: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(recipeId))
    }
}
