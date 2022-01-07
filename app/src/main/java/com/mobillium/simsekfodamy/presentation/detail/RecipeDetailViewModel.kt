package com.mobillium.simsekfodamy.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewEvent
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.handleHttpException
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.ImageList
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val user: UserRepository,
    val preferences: PreferencesManager,
    state: SavedStateHandle
) : BaseViewModel() {

    //    private val recipeLiveData = state.getLiveData<Recipe>("recipe")
    val recipe = MutableLiveData<Recipe>()
    val comment = MutableLiveData<Comment>()
    val recipeId: Int = state.get("recipeId") ?: 0
    private val _recipeDetailViewEvent = Channel<RecipeDetailViewEvent>()
    val event = _recipeDetailViewEvent.receiveAsFlow()

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
        println("toImageSlider")
        navigate(
            RecipeDetailFragmentDirections.actionRecipeDetailFragmentToImageSliderFragment(
                ImageList(recipe.value?.images!!)
            )
        )
    }

    fun getRecipeById() {
        viewModelScope.launch {
            val response = recipeRepository.getRecipeByID(recipeId)
            when (response) {
                is Result.Success -> {

                    _recipeDetailViewEvent.send(RecipeDetailViewEvent.RecipeGot(response.response))
                    recipe.value = response.response
                }
                is Result.Error -> {
                    showMessage(response.exception.handleHttpException())
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
                    _recipeDetailViewEvent.send(RecipeDetailViewEvent.FirstComment(null))
                    println("Error")
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
                    followUser(recipe?.value?.user?.id!!)
            }
        }
    }

    fun onClickAddComment() {
        viewModelScope.launch {
            if (preferences.token.first().isBlank()) {
                navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragmentToLoginWarningDialog())
            } else {
                navigate(
                    RecipeDetailFragmentDirections.actionRecipeDetailFragmentToCommentsFragment(
                        recipe.value!!.id
                    )
                )
            }
        }
    }

    fun onClickLike() = viewModelScope.launch {
        if (preferences.token.first().isBlank())
            navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragmentToLoginWarningDialog())
        else if (recipe.value!!.is_liked)
            dislikeRecipe()
        else
            likeRecipe()
    }

    fun followUser(id: Int) = viewModelScope.launch {
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
}
