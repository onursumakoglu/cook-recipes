package com.onursumakoglu.cookrecipes.domain.entity

data class Recipe (
    var id: Int? = null,
    var title: String? = null,
    var image: String? = null,
    var readyInMinutes: Int? = null,
    var spoonacularScore: Int? = null,
    var summary: String? = null,
    var servings: Int? = null
) : Entity()