package com.mobillium.simsekfodamy.presentation.bottom.comment

import androidx.lifecycle.SavedStateHandle
import com.mobillium.domain.model.Comment
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.Constants.KEY_DELETE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomCommentViewModel @Inject constructor(
    private val recipe: RecipeRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val comment: Comment? = savedStateHandle.get<Comment>(COMMENT)
    private val recipeId = savedStateHandle.get<Int>(RECIPE_ID) ?: 1
    fun edit() {
        navigate(
            BottomCommentFragmentDirections.actionBottomCommentFragmentToEditCommentFragment(
                comment?.id ?:-1,
                recipeId,
                comment?.text ?:""
            )
        )
    }

    fun delete() {
        sendRequest(
            loading = false,
            request = { comment?.let { recipe.deleteRecipeComments(recipeId = recipeId, it.id) } },
            success = {
                showMessage(R.string.delete)
                setExtras(KEY_DELETE, true)
                popBackStack()
            }
        )
    }


    companion object {
        private const val COMMENT = "comment"
        private const val RECIPE_ID = "recipeId"
        private const val SET = "set"

    }
}