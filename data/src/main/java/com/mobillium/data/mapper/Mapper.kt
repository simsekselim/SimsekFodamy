package com.mobillium.data.mapper

import com.mobillium.data.remote.response.*
import com.mobillium.domain.model.*

fun RecipeResponse.toDomainModel(): Recipe =
    Recipe(
        id = this.id,
        title = this.title ?: "",
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
        images = this.images.map {
            it.toDomainModel()
        }
    )

fun ImageResponse.toDomainModel(): Image =
    Image(
        width = this.width,
        height = this.height,
        url = this.url
    )

fun UserResponse.toDomainModel(): User =
    User(
        id = this.id,
        image = this.image?.toDomainModel(),
        name = this.name ?: "",
        username = this.username ?: "",
        favorites_count = this.favorites_count ?: 0,
        following_count = this.following_count ?: 0,
        followed_count = this.followed_count ?: 0,
        is_following = this.is_following,
        likes_count = this.likes_count ?: 0,
        recipe_count = this.recipe_count ?: 0
    )

fun TimeOfRecipeResponse.toDomainModel(): TimeOfRecipe =
    TimeOfRecipe(
        id = this.id,
        text = this.text
    )

fun NumberOfPersonResponse.toDomainModel(): NumberOfPerson =
    NumberOfPerson(
        id = this.id,
        text = this.text
    )

fun CategoryResponse.toDomainModel(): Category =
    Category(
        id = this.id,
        name = this.name ?: "",
        image = image?.toDomainModel(),
        recipes = recipes?.map {
            it.toDomainModel()
        }

    )

fun CommentResponse.toDomainModel(): Comment =
    Comment(
        id = this.id,
        text = this.text,
        user = this.user.toDomainModel(),
        difference = this.difference
    )

fun LoginResponse.toDomainModel(): Login =
    Login(
        token = this.token,
        user = this.user.toDomainModel()
    )

fun PaginationResponse.toDomainModel(): Pagination =
    Pagination(
        current_page = this.current_page,
        first_item = this.first_item,
        last_item = this.last_item,
        last_page = this.last_page,
        per_page = this.per_page,
        total = this.total
    )

fun RecipePagingResponse.toDomainModel(): RecipePaging =
    RecipePaging(
        data = this.data.map { it.toDomainModel() },
        pagination = this.pagination.toDomainModel()
    )

fun CommonResponse.toDomainModel(): Common =
    Common(
        code = this.code ?: "",
        message = this.message ?: "",
        error = this.error ?: ""
    )
