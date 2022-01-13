package com.mobillium.simsekfodamy.presentation.commentflow.comment

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.handleHttpException
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.utils.Constants.DELETED
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import com.mobillium.simsekfodamy.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: RecipeRepository,
    val preferences: PreferencesManager,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    val event = SingleLiveEvent<CommentsViewEvent>()

    val recipeComment = MutableLiveData<PagingData<Comment>>()

    val commentText = MutableLiveData("")
    val comment = MutableLiveData<Comment>()
    val recipe = stateHandle.get<Int>(RECIPE_ID) ?: 0
    private val recipeId = stateHandle.get<Int>(RECIPE_COMMENT_ID) ?: 0

    fun comments() = viewModelScope.launch {
        repository.getRecipeComments(recipeId).cachedIn(viewModelScope).collect {
            recipeComment.value = it
        }
    }

    fun sendComment() = viewModelScope.launch {
        if (preferences.getToken().isBlank()) {
            navigate(CommentsFragmentDirections.actionCommentsFragmentToLoginWarningDialog())
        } else {
            when (val response = repository.sendComment(recipeId, commentText.value.toString())) {
                is Result.Success -> {
                    event.postValue(CommentsViewEvent.SendCommentSuccess)
                }
                is Result.Error -> {
                    showMessage(response.exception.handleHttpException())
                }
            }
        }
    }

    fun toBottomSheet() {
        viewModelScope.launch {
            val userId = preferences.getUser()
            if (comment.value?.user?.id == userId) {
                navigate(CommentsFragmentDirections.actionCommentsFragmentToBottomCommentFragment())
            }
        }
    }

    fun deleteComment() = viewModelScope.launch {
        when (val response = repository.deleteRecipeComments(recipeId, comment.value?.id!!)) {
            is Result.Success -> {
                event.postValue(CommentsViewEvent.DeleteCommentSuccess)
                showMessage(DELETED)
            }
            is Result.Error -> {
                showMessage(response.exception.handleHttpException())
            }
        }
    }

    fun toEdit() = viewModelScope.launch {

        navigate(
            CommentsFragmentDirections.actionCommentsFragmentToSetCommentFragment(
                comment.value?.id!!,
                recipeId,
                comment.value!!.text!!

            )
        )
    }
    companion object {
        const val RECIPE_ID = "recipeId"
        const val RECIPE_COMMENT_ID = "recipeCommentId"

    }
}
