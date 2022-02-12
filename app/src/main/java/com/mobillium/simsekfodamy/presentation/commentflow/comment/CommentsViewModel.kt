package com.mobillium.simsekfodamy.presentation.commentflow.comment

import android.os.Bundle
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


    private var recipeId: Int? = null

    override fun fetchExtras(extras: Bundle) {
        super.fetchExtras(extras)
        recipeId = CommentsFragmentArgs.fromBundle(extras).recipeCommentId

    }

    fun comments() = viewModelScope.launch {
        sendRequest(
            request = {
                Pager(
                    config = pageConfig,
                    pagingSourceFactory = { CommentPagingFactory(repository, recipeId!!) }
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
                loading = false,
                request = {
                    repository.sendComment(recipeId!!, commentText.value.toString())
                },
                success = {
                    event.value = CommentsViewEvent.SendCommentSuccess
                    commentText.value = ""
                    showMessage(R.string.comment_add)

                }
            )
        }
    }

    fun toBottomSheet(comment: Comment) {
        viewModelScope.launch {
            val userId = preferences.getUser()
            if (comment.user.id == userId) {
                navigate(
                    CommentsFragmentDirections.actionCommentsFragmentToBottomCommentFragment(
                        recipeId!!,
                        comment
                    )
                )
            }
        }
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
