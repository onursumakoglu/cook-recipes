package com.onursumakoglu.cookrecipes.data.home

import com.onursumakoglu.cookrecipes.domain.entity.Recipe

interface HomeRepository {
    suspend fun getTodaysRecipe(): Recipe?
    suspend fun getRandomRecipes(start: Int, end: Int): List<Recipe>
}