package com.mobillium.simsekfodamy.presentation.homeflow.editor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.presentation.homeflow.home.HomeFragmentDirections
import com.mobillium.simsekfodamy.utils.RecipePagingFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository

) : BaseViewModel() {

    val recipes = MutableLiveData<PagingData<Recipe>>()

    init {
        editorData()
    }

    private fun editorData() {
        sendRequest(
            request = {
                Pager(
                    config = pageConfig,
                    pagingSourceFactory = {
                        RecipePagingFactory(
                            recipeRepository,
                            GET_EDITOR_CHOICE,
                            null
                        )
                    }
                ).flow
            },
            success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect {
                        recipes.value = it
                    }
                }
            }
        )
    }
    fun editor(recipeId: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(recipeId))
    }
    companion object {
        private val pageConfig = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        )

        const val GET_EDITOR_CHOICE = "GET_EDITOR_CHOICE"
    }
}
