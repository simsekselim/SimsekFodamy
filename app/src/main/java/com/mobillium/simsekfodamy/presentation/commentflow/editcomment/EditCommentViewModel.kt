package com.mobillium.simsekfodamy.presentation.commentflow.editcomment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCommentViewModel @Inject constructor(
    private val repository: RecipeRepository,
    val preferences: PreferencesManager,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    val event = SingleLiveEvent<EditCommentViewEvent>()

    private val recipeId = stateHandle.get<Int>(RECIPE_ID) ?: 0
    private val commentId = stateHandle.get<Int>(COMMENT_ID) ?: 0
    private val commentText = stateHandle.get<String>(COMMENT_TEXT) ?: ""

    val editableComment = MutableLiveData(commentText)

    fun onClickSave() =
        sendRequest(
            request = {
                repository.editRecipeComments(
                    recipeId,
                    commentId,
                    editableComment.value.toString()
                )
            },
            success = {
                popBackStack(R.id.commentsFragment)
            }
        )


    companion object {
        const val RECIPE_ID = "recipeId"
        const val COMMENT_ID = "commentId"
        const val COMMENT_TEXT = "commentText"
    }
}
