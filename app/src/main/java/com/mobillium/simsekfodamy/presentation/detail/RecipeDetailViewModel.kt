package com.mobillium.simsekfodamy.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.handleHttpException
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.ImageList
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import com.mobillium.simsekfodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val user: UserRepository,
    val preferences: PreferencesManager,
    state: SavedStateHandle
) : BaseViewModel() {


    val recipe = MutableLiveData<Recipe>()
    val comment = MutableLiveData<Comment>()
    val recipeId: Int = state.get(RECIPE_ID) ?: 0
    val event = SingleLiveEvent<RecipeDetailViewEvent>()

    init {
        getRecipeById()
        getFirstComment()
    }

    fun toComment(commentRecipeId: Int) {
        navigate(
            RecipeDetailFragmentDirections.actionRecipeDetailFragmentToCommentsFragment(
                commentRecipeId
            )
        )
    }

    fun toImageSlider() {
        navigate(
            RecipeDetailFragmentDirections.actionRecipeDetailFragmentToImageSliderFragment(
                ImageList(recipe.value?.images!!)
            )
        )
    }

    fun getRecipeById() {
        viewModelScope.launch {
            when (val response = recipeRepository.getRecipeByID(recipeId)) {
                is Result.Success -> {

                    event.postValue(RecipeDetailViewEvent.RecipeGot(response.response))
                    recipe.value = response.response
                }
                is Result.Error -> {
                    showMessage(response.exception.handleHttpException())
                }
            }
        }
    }

    fun getFirstComment() {
        viewModelScope.launch {
            when (val response = recipeRepository.getFirstComment(recipeId)) {
                is Result.Success -> {

                    comment.value = response.response
                }
                is Result.Error -> {
                    event.postValue(RecipeDetailViewEvent.FirstComment(null))
                }
            }
        }
    }

    fun onClickFollowButton() {
        viewModelScope.launch {
            if (preferences.token.first().isBlank()) {
                navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragmentToLoginWarningDialog())
            } else {

                if (recipe.value?.user?.is_following!!)
                    navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragmentToBottomSheetFragment())
                else
                    followUser(recipe.value?.user?.id!!)
            }
        }
    }

    fun onClickLike() = viewModelScope.launch {
        when {
            preferences.token.first()
                .isBlank() -> navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragmentToLoginWarningDialog())
            recipe.value!!.is_liked -> dislikeRecipe()
            else -> likeRecipe()
        }
    }

    private fun followUser(id: Int) = viewModelScope.launch {
        when (val response = user.followUser(id)) {
            is Result.Success -> {
                getRecipeById()
            }
            is Result.Error -> {
                response.exception.handleHttpException()
            }
        }
    }

    fun unfollowUser() = viewModelScope.launch {
        when (val response = user.unfollowUser(recipe.value!!.user.id)) {
            is Result.Success -> {
                getRecipeById()
            }
            is Result.Error -> {
                response.exception.handleHttpException()
            }
        }
    }

    private fun likeRecipe() = viewModelScope.launch {
        recipe.value!!.apply {
            if (!is_liked) {
                when (val response = recipeRepository.likeRecipe(id)) {
                    is Result.Success -> {
                        getRecipeById()
                    }
                    is Result.Error -> {
                        response.exception.handleHttpException()
                    }
                }
            }
        }
    }

    private fun dislikeRecipe() = viewModelScope.launch {
        recipe.value!!.apply {
            if (is_liked) {
                when (val response = recipeRepository.dislikeRecipe(id)) {
                    is Result.Success -> {
                        getRecipeById()
                    }
                    is Result.Error -> {
                        response.exception.handleHttpException()
                    }
                }
            }
        }
    }

    companion object {
        const val RECIPE_ID = "recipeId"

    }
}
