package com.mobillium.simsekfodamy.presentation.commentflow.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.handleHttpException
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.User
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.repository.UserRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import com.mobillium.simsekfodamy.utils.Constants.DELETED
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.http.DELETE
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: RecipeRepository,
    private val userRepository: UserRepository,
    val preferences: PreferencesManager,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _eventChannel = Channel<CommentsViewEvent>()
    val event = _eventChannel.receiveAsFlow()


    val commentText = MutableLiveData("")
    val comment = MutableLiveData<Comment>()
    val recipe = stateHandle.get<Int>("recipeId") ?: 0
    private val recipeId = stateHandle.get<Int>("recipeCommentId") ?: 0
    private val commentsFlow = repository.getRecipeComments(recipeId).cachedIn(viewModelScope)
    val comments = commentsFlow.asLiveData()

    fun sendComment() = viewModelScope.launch {
        if (preferences.getToken().isNullOrBlank()) {
            navigate(CommentsFragmentDirections.actionCommentsFragmentToLoginWarningDialog())
        } else {
            when (val response = repository.sendComment(recipeId, commentText.value.toString())) {
                is Result.Success -> {
                    _eventChannel.send(CommentsViewEvent.SendCommentSuccess)
                }
                is Result.Error -> {
                    println(response.exception.handleHttpException())
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
        when (repository.deleteRecipeComments(recipeId, comment.value?.id!!)) {
            is Result.Success -> {
                _eventChannel.send(CommentsViewEvent.DeleteCommentSuccess)
                showMessage(DELETED)
            }
            is Result.Error -> {

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

}

