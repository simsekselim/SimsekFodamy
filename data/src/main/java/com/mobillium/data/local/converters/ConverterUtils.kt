package com.mobillium.data.local.converters

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.lang.reflect.Type

inline fun <reified T> type(): Type = object : TypeToken<T>() {}.type

inline fun <reified T> toJson(src: Any): String {
    return Gson().toJson(src, type<T>())
}

inline fun <reified T> fromJson(src: String): T {
    return Gson().fromJson(src, type<T>())
}
