package com.onursumakoglu.cookrecipes.data.home.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipeDTO (
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("readyInMinutes") var readyInMinutes: Int? = null,
    @SerializedName("spoonacularScore") var spoonacularScore: Int? = null,
    @SerializedName("summary") var summary: String? = null,
    @SerializedName("servings") var servings: Int? = null
)