package com.onursumakoglu.cookrecipes.presentation.recipes

import android.content.Context
import android.view.Window
import com.onursumakoglu.cookrecipes.domain.entity.Recipe

class Singleton {
    companion object{
        private lateinit var recipeInfoDialog: RecipeInfoDialog

        fun showUserInfoDialog(context: Context, recipe: Recipe){
            recipeInfoDialog = RecipeInfoDialog(context, recipe)
            recipeInfoDialog.setCancelable(true)
            recipeInfoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            recipeInfoDialog.show()

        }
    }
}