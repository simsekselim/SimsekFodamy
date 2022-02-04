package com.mobillium.simsekfodamy.presentation.commentflow.comment

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.data.utils.PreferencesManager
import com.mobillium.domain.model.Comment
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.simsekfodamy.R
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.utils.CommentPagingFactory
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
        sendRequest(
            request = {
                Pager(
                    config = pageConfig,
                    pagingSourceFactory = { CommentPagingFactory(repository, recipeId) }
                ).flow
            },
            success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect {
                        recipeComment.value = it
                    }
                }
            }
        )
    }

    fun sendComment() = viewModelScope.launch {
        if (preferences.getToken().isBlank()) {
            navigate(CommentsFragmentDirections.actionCommentsFragmentToLoginWarningDialog())
        } else {
            sendRequest(
                request = {
                    repository.sendComment(recipeId, commentText.value.toString())
                },
                success = {
                    commentText.value = ""
                    showMessage(R.string.comment_add)

                }
            )
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
        sendRequest(
            request = { repository.deleteRecipeComments(recipeId, comment.value?.id!!) },
            success = {
                event.value = CommentsViewEvent.DeleteCommentSuccess
            }
        )
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
        private val pageConfig = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        )
        const val RECIPE_ID = "recipeId"
        const val RECIPE_COMMENT_ID = "recipeCommentId"
    }
}
