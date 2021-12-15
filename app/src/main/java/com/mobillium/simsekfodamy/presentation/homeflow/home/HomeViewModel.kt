package com.mobillium.simsekfodamy.presentation.homeflow.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.model.Recipe
import com.mobillium.simsekfodamy.repository.RecipeRepository
import com.mobillium.simsekfodamy.utils.ActionLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository,

    ) : BaseViewModel() {
    companion object {
        const val LAST_ADDED = "LAST_ADDED"
        const val EDITOR_CHOICE = "EDITOR_CHOICE"
    }

    val navigateMain = ActionLiveData()












}


