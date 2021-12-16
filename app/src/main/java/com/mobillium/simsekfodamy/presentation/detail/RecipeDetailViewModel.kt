package com.mobillium.simsekfodamy.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.mobillium.simsekfodamy.utils.Result


@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    //    private val recipeLiveData = state.getLiveData<Recipe>("recipe")
    val recipe = MutableLiveData<Recipe>()
    val comment = MutableLiveData<Comment>()
    val recipeId: Int = state.get("recipeId") ?: 0

    init {
        getRecipeById()
        getFirstComment()
    }

    fun getRecipeById() {

        viewModelScope.launch {
            val response = recipeRepository.getRecipeByID(recipeId)
            when (response) {
                is Result.Success -> {

                    recipe.value = response.response

                }
                is Result.Error -> {

                    println("Error")

                }
            }


        }


    }

    fun getFirstComment() {

        viewModelScope.launch {
            val response = recipeRepository.getFirstComment(recipeId)
            when (response) {
                is Result.Success -> {

                    comment.value = response.response

                }
                is Result.Error -> {

                    println("Error")

                }
            }


        }


    }

}