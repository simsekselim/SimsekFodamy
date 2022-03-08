package com.mobillium.simsekfodamy.presentation.homeflow.last

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mobillium.domain.model.Recipe
import com.mobillium.domain.repository.RecipeRepository
import com.mobillium.simsekfodamy.base.BaseViewModel
import com.mobillium.simsekfodamy.presentation.homeflow.home.HomeFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LastViewModel
@Inject
constructor(
    private val recipeRepository: RecipeRepository

) : BaseViewModel() {

    val recipes = MutableLiveData<PagingData<Recipe>>()

    init {
        lastData()
    }

    private fun lastData() {
        sendRequest(
            request = {
                recipeRepository.getLastAddedPaging()
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
    fun detail(recipeId: Int) {
        navigate(HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(recipeId))
    }
    companion object {
        private val pageConfig = PagingConfig(
            pageSize = 24,
            maxSize = 100,
            enablePlaceholders = false
        )

        const val GET_LAST = "GET_LAST"
    }
}
