package com.mobillium.simsekfodamy.presentation.detail

import com.mobillium.simsekfodamy.model.Comment
import com.mobillium.simsekfodamy.model.Recipe

sealed class RecipeDetailViewEvent {
    data class FirstComment(val comment: Comment?) : RecipeDetailViewEvent()
    data class RecipeGot(val recipe : Recipe) : RecipeDetailViewEvent()
}
