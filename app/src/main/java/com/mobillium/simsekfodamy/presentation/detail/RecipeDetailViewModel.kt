package com.mobillium.simsekfodamy.presentation.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.model.Comment
import com.mobillium.domain.model.ImageList
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.domain.repository.UserRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository,
    private val user: UserRepository,
    val preferences: PreferencesManager
) : BaseViewModel() {
    private val _recipe: MutableLiveData<Recipe> = MutableLiveData()
    val recipe: LiveData<Recipe> get() = _recipe
    val comment = MutableLiveData<Comment>()
    var recipeId: Int? = null
    val event = SingleLiveEvent<RecipeDetailViewEvent>()

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        recipeId = RecipeDetailFragmentArgs.fromBundle(extras).recipeId
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
                ImageList(recipe.value!!.images)
            )
        )
    }

    fun getRecipeById() {
        sendRequest(
            request = { recipeRepository.getRecipe(recipeId!!) },
            success = {
                _recipe.value = it
            }
        )
    }

    private fun getFirstComment() {
        sendRequest(
            request = { recipeRepository.getFirstComment(recipeId!!) },
            success = {
                comment.value = it
            }
        )
    }

    fun onClickFollowButton() {
        viewModelScope.launch {
            if (preferences.token.first().isBlank()) {
                navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragmentToLoginWarningDialog())
            } else {

                if (recipe.value?.user?.is_following!!)
                    navigate(
                        RecipeDetailFragmentDirections.actionRecipeDetailFragmentToBottomSheetFragment(
                            recipe.value?.user?.id!!
                        )
                    )
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

    private fun followUser(id: Int) {
        sendRequest(
            request = { user.followUser(id) },
            success = {
                showMessage(it.message)
                getRecipeById()
            }
        )
    }

    private fun likeRecipe() {
        sendRequest(
            request = { recipeRepository.likeRecipe(recipeId!!) },
            success = {
                showMessage(it.message)
                getRecipeById()
            }
        )
    }

    private fun dislikeRecipe() {
        sendRequest(
            request = { recipeRepository.dislikeRecipe(recipeId!!) },
            success = {
                showMessage(it.message)
                getRecipeById()
            }
        )
    }

}
