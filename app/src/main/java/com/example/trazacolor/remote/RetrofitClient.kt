package com.example.trazacolor.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private const val URL_BASE = "http://demo4058102.mockable.io/"
        fun getApiClient(): IApiClient {
            val mRetrofit = Retrofit.Builder().baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return mRetrofit.create(IApiClient::class.java)
        }
    }
}