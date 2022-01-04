package com.onursumakoglu.cookrecipes.data.dto

import com.google.gson.annotations.SerializedName

data class RecipeDTO (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("readyInMinutes") var readyInMinutes: Int? = null,
    @SerializedName("spoonacularScore") var spoonacularScore: Double? = null
)