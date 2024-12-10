package com.noureldin.foodhub.data

import com.noureldin.foodhub.data.model.AuthResponse
import com.noureldin.foodhub.data.model.SignInRequest
import com.noureldin.foodhub.data.model.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface FoodApi {
    @GET("/food")
    suspend fun getFood(): List<String>

    @POST("/auth/signup")
    suspend fun signup(@Body request: SignUpRequest): AuthResponse

    @POST("/auth/login")
    suspend fun signIn(@Body request: SignInRequest): AuthResponse
}