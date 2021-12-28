package com.mobillium.simsekfodamy.presentation.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.handleHttpException
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import com.mobillium.simsekfodamy.utils.PreferencesManager
import com.mobillium.simsekfodamy.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: RecipeRepository,
    val preferences: PreferencesManager,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _eventChannel = Channel<CommentsViewEvent>()
    val event = _eventChannel.receiveAsFlow()
    val navigate = ActionLiveData()

    val commentText = MutableLiveData("")

    private val recipeId = stateHandle.get<Int>("recipeCommentId") ?: 0
    private val commentsFlow = repository.getRecipeComments(recipeId).cachedIn(viewModelScope)
    val comments = commentsFlow.asLiveData()

    fun sendComment() = viewModelScope.launch {
        if (preferences.getToken().isNullOrBlank()) {
            navigate.call()
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
}

//    init {
//        commentRecipe()
//    }
//
//    fun commentRecipe() {
//        viewModelScope.launch {
//
//
//            repository.getRecipeComments(recipeId).cachedIn(viewModelScope) collect {
//                comments.value = it
//            }
//
//
//        }
//
//
//    }
