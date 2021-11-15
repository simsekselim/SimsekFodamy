package com.mobillium.simsekfodamy.domain.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService : ApiService


    fun getApiService(): ApiService {
        if(!::apiService.isInitialized){
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }


     fun getOkhttpClient() : OkHttpClient {
        val log = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder().addInterceptor(log).build()
    }



}


