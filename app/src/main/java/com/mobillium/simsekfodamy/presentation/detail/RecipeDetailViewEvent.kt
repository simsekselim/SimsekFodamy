package com.mobillium.simsekfodamy.presentation.detail

import com.mobillium.domain.model.Comment
import com.mobillium.domain.model.Recipe

sealed class RecipeDetailViewEvent {
    data class FirstComment(val comment: Comment?) : RecipeDetailViewEvent()
    data class RecipeGot(val recipe: Recipe) : RecipeDetailViewEvent()
}
