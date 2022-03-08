package com.mobillium.data.local.converters

import androidx.room.TypeConverter
import com.mobillium.data.local.model.CategoryLocal

class CategoryConverter {

    @TypeConverter
    fun categoryToJson(category: CategoryLocal): String {
        return toJson<CategoryLocal>(category)
    }

    @TypeConverter
    fun jsonToCategory(category: String): CategoryLocal {
        return fromJson(category)
    }
}
