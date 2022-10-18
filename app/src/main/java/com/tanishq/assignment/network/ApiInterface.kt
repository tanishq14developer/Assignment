package com.tanishq.assignment.network

import com.tanishq.assignment.model.ApiBody
import com.tanishq.assignment.model.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("kyc/emailGenderDob")
    suspend fun postDetails(@Body apiBody: ApiBody):Response<ApiResponse>
}