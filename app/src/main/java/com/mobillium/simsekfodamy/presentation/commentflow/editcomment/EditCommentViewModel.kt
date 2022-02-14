package com.mobillium.simsekfodamy.presentation.commentflow.editcomment

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditCommentViewModel @Inject constructor(
    private val repository: RecipeRepository,
    val preferences: PreferencesManager
) : BaseViewModel() {

    val event = SingleLiveEvent<EditCommentViewEvent>()

    var recipeId: Int? = null
    private var commentId: Int? = null

    val editableComment = MutableLiveData<String>()

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        recipeId = EditCommentFragmentArgs.fromBundle(extras).recipeId
        commentId = EditCommentFragmentArgs.fromBundle(extras).commentId
        editableComment.value = EditCommentFragmentArgs.fromBundle(extras).commentText
    }

    fun onClickSave() =
        sendRequest(
            loading = false,
            request = {
                repository.editRecipeComments(
                    recipeId!!,
                    commentId!!,
                    editableComment.value.toString()
                )
            },
            success = {
                popBackStack()
            }
        )
}
