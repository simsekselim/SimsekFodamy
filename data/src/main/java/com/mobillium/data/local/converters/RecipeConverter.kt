package com.mobillium.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.mobillium.data.local.model.RecipeLocal

@ProvidedTypeConverter
class RecipeConverter {

    @TypeConverter
    fun recipeListToJson(recipes: List<RecipeLocal>): String {
        return toJson<List<RecipeLocal>>(recipes)
    }

    @TypeConverter
    fun jsonToRecipeList(recipes: String): List<RecipeLocal> {
        return fromJson(recipes)
    }
}
