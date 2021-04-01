package com.example.trazacolor.remote

import com.example.trazacolor.local.ResponseMaster
import retrofit2.Response
import retrofit2.http.GET

interface IApiClient {
    @GET("trazacolor")
    suspend fun getFetchIndividuales(): Response<ResponseMaster>
}