package com.mobillium.simsekfodamy.presentation.detail

import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.Recipe

sealed class RecipeDetailViewEvent {
    data class FetchFirstComment(val comment: Comment?) : RecipeDetailViewEvent()
    data class RecipeFetched(val recipe : Recipe) : RecipeDetailViewEvent()
}
