package com.onursumakoglu.cookrecipes.domain

data class Cook (
    var id: Int? = null,
    var title: String? = null,
    var image: String? = null,
    var readyInMinutes: Int? = null,
    var spoonacularScore: Int? = null
) : Entity()