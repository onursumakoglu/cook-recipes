package com.onursumakoglu.cookrecipes.domain.entity

class HomeEntity (
    val todaysRecipe: Recipe?,
    val randomRecipe: List<Recipe>
    ): Entity()