package com.onursumakoglu.cookrecipes.presentation.common

import com.onursumakoglu.cookrecipes.domain.entity.Recipe

data class RandomRecipeList(
    var list: MutableList<Recipe>?
)