package com.mobillium.simsekfodamy.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
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
    val preferences: PreferencesManager,
    private val state: SavedStateHandle
) : BaseViewModel() {

    val recipe = MutableLiveData<Recipe>()
    val comment = MutableLiveData<Comment>()
    val recipeId: Int = state.get(RECIPE_ID) ?: 0
    val event = SingleLiveEvent<RecipeDetailViewEvent>()

    init {
        getRecipe()
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


    private fun getRecipe() {
            sendRequest(
                request = {recipeRepository.getRecipe(recipeId)},
                success = {
                    event.value = RecipeDetailViewEvent.RecipeGot(it)
                    recipe.value = it
                }
            )

    }

    private fun getFirstComment() {
        sendRequest(
            request = {recipeRepository.getFirstComment(recipeId)},
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

    private fun followUser(id: Int) {
        sendRequest(
            request = {user.followUser(id)},
            success = {
                showMessage(it.message)
                getRecipe()
            }
        )
    }

    fun unfollowUser() {
        sendRequest(
            request = {user.unfollowUser(recipe.value!!.user.id)},
            success = {
                showMessage(it.message)
                getRecipe()
            }
        )
    }

    private fun likeRecipe() {
        sendRequest(
            request = {recipeRepository.likeRecipe(recipeId)},
            success = {
                showMessage(it.message)
                getRecipe()
            }
        )

    }

    private fun dislikeRecipe() {
        sendRequest(
            request = {recipeRepository.dislikeRecipe(recipeId)},
            success = {
                showMessage(it.message)
                getRecipe()
            }
        )

    }

    companion object {
        const val RECIPE_ID = "recipeId"
    }
}
