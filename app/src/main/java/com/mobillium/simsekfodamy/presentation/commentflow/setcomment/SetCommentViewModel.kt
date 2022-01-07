package com.mobillium.simsekfodamy.presentation.commentflow.setcomment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.presentation.commentflow.comment.CommentsViewEvent
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.utils.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.mobillium.simsekfodamy.utils.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SetCommentViewModel @Inject constructor(
    private val repository: RecipeRepository,
    val preferences: PreferencesManager,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _eventChannel = Channel<SetCommentViewEvent>()
    val event = _eventChannel.receiveAsFlow()

    private val recipeId = stateHandle.get<Int>("recipeId") ?: 0
    private val commentId = stateHandle.get<Int>("commentId") ?: 0
    private val commentText = stateHandle.get<String>("commentText")?: ""

    val editableComment = MutableLiveData<String>(commentText)

    fun onClickSave() = viewModelScope.launch {
        println(recipeId)
        println(commentId)
        when (val response =
            repository.editRecipeComments(
                recipeId,
                commentId,
                editableComment.value.toString()
            )) {
            is Result.Success -> {
                _eventChannel.send(SetCommentViewEvent.EditCommentSuccess)

            }
            is Result.Error -> {
                println("Doesnt WorkS")
            }
        }

    }


}