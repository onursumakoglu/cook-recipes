package com.onursumakoglu.cookrecipes.data.home.repository

import com.onursumakoglu.cookrecipes.data.home.api.HomeAPI
import com.onursumakoglu.cookrecipes.data.home.dto.RecipeDTO
import com.onursumakoglu.cookrecipes.domain.entity.Recipe

class HomeRepoImp(private var homeApi: HomeAPI) : HomeRepository {

    override suspend fun getTodaysRecipe(): Recipe? {
        val response = homeApi.randomRecipes(1, "", false, "0032005913964ef488db5b4e714be885")
        var recipe: Recipe? = null
        val recipeDTO: RecipeDTO?

        if(response.isSuccessful){

            recipeDTO = response.body()?.recipes?.get(0)

            recipe = recipeDTO?.let {
                Recipe(
                    id = it.id?:0,
                    title = it.title,
                    image = it.image,
                    readyInMinutes = it.readyInMinutes,
                    spoonacularScore = it.spoonacularScore,
                    summary = it.summary,
                    servings = it.servings
                )
            }
        }
        return recipe
    }

    override suspend fun getRandomRecipes(start: Int, end: Int): List<Recipe> {
        val response = homeApi.randomRecipes(20, "", false, "0032005913964ef488db5b4e714be885")

        if (response.isSuccessful){

            return response.body()?.recipes?.map {
                Recipe(
                    id = it.id,
                    title = it.title,
                    image = it.image,
                    readyInMinutes = it.readyInMinutes,
                    spoonacularScore = it.spoonacularScore,
                    summary = it.summary,
                    servings = it.servings
                )

            } ?: listOf()
        }
        return listOf()
    }

}