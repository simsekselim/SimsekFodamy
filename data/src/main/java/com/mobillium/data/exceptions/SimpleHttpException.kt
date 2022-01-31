package com.mobillium.data.exceptions

data class SimpleHttpException(
    val code: String?,
    val friendlyMessage: String?
) : Exception()