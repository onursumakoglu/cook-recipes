package com.onursumakoglu.cookrecipes.data.home.remote.dto

import com.google.gson.annotations.SerializedName

data class RandomRecipeResponse (
    @SerializedName("recipes") var recipes: List<RecipeDTO>? = null
)