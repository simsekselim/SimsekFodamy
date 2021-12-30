package com.mobillium.simsekfodamy.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserInterceptor @Inject constructor(
    private val preferences: PreferencesManager,
) : Interceptor {
    private var token: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        setToken()
        val request = chain.request().newBuilder()
        if (token.isNullOrBlank()) return chain.proceed(request.build())
        request.addHeader(ADD_TOKEN, token.toString())
        return chain.proceed(request.build())
    }

    companion object {
        private const val ADD_TOKEN = "X-Fodamy-Token"
    }

    private fun setToken() = CoroutineScope(SupervisorJob() + Dispatchers.IO).launch {
        token = preferences.getToken()
    }
}
