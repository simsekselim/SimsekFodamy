package com.mobillium.data.mapper

import com.mobillium.data.local.model.*
import com.mobillium.domain.model.*

fun RecipeLocal.toDomainModel(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        definition = this.definition,
        ingredients = this.ingredients,
        directions = this.directions,
        difference = this.difference,
        is_editor_choice = this.is_editor_choice,
        is_liked = this.is_liked,
        like_count = this.like_count,
        comment_count = this.comment_count,
        user = this.user.toDomainModel(),
        time_of_recipe = this.time_of_recipe.toDomainModel(),
        number_of_person = this.number_of_person.toDomainModel(),
        category = this.category.toDomainModel(),
        images = this.images.map { it.toDomainModel() }
    )
}

fun UserLocal.toDomainModel(): User {
    return User(
        id = this.id,
        image = this.image.toDomainModel(),
        name = this.name,
        username = this.username,
        favorites_count = this.favorites_count,
        followed_count = this.followed_count,
        following_count = this.following_count,
        is_following = this.is_following,
        likes_count = this.likes_count,
        recipe_count = this.recipe_count
    )
}

fun ImageLocal.toDomainModel(): Image {
    return Image(
        height = this.height,
        width = this.width,
        url = this.url
    )
}

fun TimeOfRecipeLocal.toDomainModel(): TimeOfRecipe {
    return TimeOfRecipe(id, text)
}

fun NumberOfPersonLocal.toDomainModel(): NumberOfPerson {
    return NumberOfPerson(id, text)
}

fun CategoryLocal.toDomainModel(): Category {
    return Category(
        id = this.id,
        name = this.name,
        image = this.image.toDomainModel(),
        recipes = this.recipes.map { it.toDomainModel() }
    )
}

fun CommentLocal.toDomainModel(): Comment {
    return Comment(
        id = this.id,
        text = this.text,
        user = this.user.toDomainModel(),
        difference = this.difference
    )
}
