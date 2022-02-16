package com.mobillium.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val category: Category,
    val comment_count: Int,
    val definition: String,
    val difference: String,
    val directions: String,
    val id: Int,
    val images: List<Image>,
    val ingredients: String,
    val is_editor_choice: Boolean,
    val is_liked: Boolean,
    val like_count: Int,
    val number_of_person: NumberOfPerson,
    val time_of_recipe: TimeOfRecipe,
    val title: String,
    val user: User
) : Parcelable
