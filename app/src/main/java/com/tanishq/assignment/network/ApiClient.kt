package com.tanishq.assignment.network

import com.tanishq.assignment.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private fun getokHttpClient() : OkHttpClient {
        ////headerInterceptor
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)  /** this line comment when you publish app */
            .build()
    }
    private fun getRetrofitBuilder(baseUrl: String): Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getokHttpClient())
    }

    fun <T> getRetrofitService(dyClass : Class<T>) : T {
        return getRetrofitBuilder(AppConstants.BASE_URL).build().create(dyClass)

    }
}