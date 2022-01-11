package com.onursumakoglu.cookrecipes.data.home.repository

import com.onursumakoglu.cookrecipes.domain.entity.Recipe

interface CommonRepository {
    suspend fun getTodaysRecipe(): Recipe?
    suspend fun getRandomRecipes(start: Int, end: Int): List<Recipe>
}