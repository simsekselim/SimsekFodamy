package com.mobillium.data.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.mobillium.data.local.model.ImageLocal

@ProvidedTypeConverter
class ImageConverter {

    @TypeConverter
    fun imageListToJson(images: List<ImageLocal>): String {
        return toJson<List<ImageLocal>>(images)
    }

    @TypeConverter
    fun jsonToImageList(imageListSrc: String): List<ImageLocal> {
        return fromJson(imageListSrc)
    }
}
