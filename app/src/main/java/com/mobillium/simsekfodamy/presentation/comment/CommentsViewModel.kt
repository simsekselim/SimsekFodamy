package com.mobillium.simsekfodamy.presentation.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val repository: RecipeRepository,
    stateHandle: SavedStateHandle
) : BaseViewModel() {

    val commentText = MutableLiveData("")
    private val recipeId = stateHandle.get<Int>("commentId") ?: 0
    val comments: MutableLiveData<PagingData<Comment>> = MutableLiveData()


//    init {
//      commentRecipe()
//     }

//     fun commentRecipe() {
//         viewModelScope.launch {


//             repository.getRecipeComments(recipeId).cachedIn(viewModelScope)collect {
//                comments.value = it
//      }


//   }


//   }
}