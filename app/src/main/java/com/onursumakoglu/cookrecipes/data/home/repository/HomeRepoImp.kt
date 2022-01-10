package com.onursumakoglu.cookrecipes.data.home.repository

import com.onursumakoglu.cookrecipes.data.home.local.HomeDatabase
import com.onursumakoglu.cookrecipes.data.home.local.TodayRecipeEntity
import com.onursumakoglu.cookrecipes.data.home.remote.api.HomeAPI
import com.onursumakoglu.cookrecipes.data.home.remote.dto.RecipeDTO
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import java.text.SimpleDateFormat
import java.util.*

class HomeRepoImp(private var homeApi: HomeAPI, private var homeDatabase: HomeDatabase) : HomeRepository {

    override suspend fun getTodaysRecipe(): Recipe? {


        val currentDate = SimpleDateFormat("yyyy-MM-dd").format(Date())
        var localRecipe: TodayRecipeEntity? = null

        homeDatabase.todayRecipeDao().get(currentDate)?.day?.let {
            localRecipe = homeDatabase.todayRecipeDao().get(it)
        }

        if (localRecipe == null){

            val response = homeApi.randomRecipes(1, "", false, "896a50a4a6d3429fb2f269b9edf60ac7")

            var recipe: Recipe? = null
            val recipeDTO: RecipeDTO?

            if (response.isSuccessful){

                recipeDTO = response.body()?.recipes?.get(0)

                 recipeDTO?.run {
                   recipe =  Recipe(
                        id = this.id,
                        title = this.title,
                        image = this.image,
                        readyInMinutes = this.readyInMinutes,
                        spoonacularScore = this.spoonacularScore,
                        summary = this.summary,
                        servings = this.servings
                    )
                     localRecipe = TodayRecipeEntity(
                         day = currentDate,
                         id = this.id,
                         title = this.title,
                         image = this.image,
                         spoonacularScore = this.spoonacularScore,
                         readyInMinutes = this.readyInMinutes,
                         summary = this.summary,
                         servings = this.servings
                     )
                }

                localRecipe?.let {
                    homeDatabase.todayRecipeDao().insert(it)
                }
                return recipe
            }

        }

        else{
            return localRecipe?.let {
                Recipe(
                    id = it.id,
                    title = it.title,
                    image = it.image,
                    readyInMinutes = it.readyInMinutes,
                    spoonacularScore = it.spoonacularScore,
                    summary = it.summary,
                    servings = it.servings
                )
            }

        }

        return localRecipe




        /*
        val response = homeApi.randomRecipes(1, "", false, "896a50a4a6d3429fb2f269b9edf60ac7")
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

         */


    }

    override suspend fun getRandomRecipes(start: Int, end: Int): List<Recipe> {
        val response = homeApi.randomRecipes(20, "", false, "896a50a4a6d3429fb2f269b9edf60ac7")

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