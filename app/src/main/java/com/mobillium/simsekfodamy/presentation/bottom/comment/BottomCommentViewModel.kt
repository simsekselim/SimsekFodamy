package com.mobillium.simsekfodamy.presentation.bottom.comment

import android.os.Bundle
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
) : BaseViewModel() {
    var recipeId: Int? = null
    var comment: Comment? = null

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        comment = BottomCommentFragmentArgs.fromBundle(extras).comment
        recipeId = BottomCommentFragmentArgs.fromBundle(extras).recipeId
    }
    fun edit() {
        navigate(
            BottomCommentFragmentDirections.actionBottomCommentFragmentToEditCommentFragment(
                comment?.id ?: -1,
                recipeId!!,
                comment?.text ?: ""
            )
        )
    }

    fun delete() {
        sendRequest(
            loading = false,
            request = { comment?.let { recipe.deleteRecipeComments(recipeId = recipeId!!, it.id) } },
            success = {
                showMessage(R.string.delete)
                setExtras(KEY_DELETE, true)
                popBackStack()
            }
        )
    }
}
