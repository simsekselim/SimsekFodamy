package com.mobillium.data.mapper

import com.mobillium.data.local.model.*
import com.mobillium.data.remote.response.*

fun RecipeResponse.toLocalModel(is_last_added: Boolean = false): RecipeLocal {
    return RecipeLocal(
        id = this.id,
        title = this.title,
        definition = this.definition,
        ingredients = this.ingredients,
        directions = this.directions,
        difference = this.difference,
        is_editor_choice = this.is_editor_choice,
        is_last_added = is_last_added,
        is_liked = this.is_liked,
        like_count = this.like_count,
        comment_count = this.comment_count,
        user = this.user.toLocalModel(),
        time_of_recipe = this.time_of_recipe.toLocalModel(),
        number_of_person = this.number_of_person.toLocalModel(),
        category = this.category.toLocalModel(),
        images = this.images.map { it.toLocalModel() }
    )
}

fun ImageResponse.toLocalModel(): ImageLocal {
    return ImageLocal(
        width = this.width,
        height = this.height,
        url = this.url,
    )
}

fun UserResponse.toLocalModel(): UserLocal {
    return UserLocal(
        id = this.id,
        name = this.name ?: "",
        username = this.username ?: "",
        favorites_count = this.favorites_count ?: 0,
        followed_count = this.followed_count ?: 0,
        following_count = this.following_count ?: 0,
        is_following = this.is_following,
        likes_count = this.likes_count ?: 0,
        recipe_count = this.recipe_count ?: 0,
        image = this.image?.toLocalModel() ?: ImageLocal.EMPTY
    )
}

fun TimeOfRecipeResponse.toLocalModel(): TimeOfRecipeLocal {
    return TimeOfRecipeLocal(id = this.id, text = this.text)
}

fun NumberOfPersonResponse.toLocalModel(): NumberOfPersonLocal {
    return NumberOfPersonLocal(id = this.id, text = this.text)
}

fun CategoryResponse.toLocalModel(): CategoryLocal {
    return CategoryLocal(
        id = this.id,
        name = this.name,
        recipes = this.recipes?.map { it.toLocalModel() } ?: emptyList(),
        image = this.image?.toLocalModel() ?: ImageLocal.EMPTY
    )
}

fun CommentResponse.toLocalModel(recipeId: Int): CommentLocal {
    return CommentLocal(
        id = this.id,
        text = this.text,
        difference = this.difference,
        user = this.user.toLocalModel(),
        recipeId = recipeId
    )
}
