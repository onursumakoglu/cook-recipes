package com.onursumakoglu.cookrecipes.data.api

import com.onursumakoglu.cookrecipes.data.dto.RecipeDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeAPI {
    @GET("recipes/random")
    fun randomRecipes(@Query("number") number: Int,
    @Query("tags") tags: String,
    @Query("limitLicense") limitLicense: Boolean,
    @Query("apiKey") apiKey: String) : Response<RecipeDTO>
}