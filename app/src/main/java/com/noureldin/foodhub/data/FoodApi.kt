package com.noureldin.foodhub.data

import retrofit2.http.GET


interface FoodApi {
    @GET("/food")
    suspend fun getFood(): List<String>
}