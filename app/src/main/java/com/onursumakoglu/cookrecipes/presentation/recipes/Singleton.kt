package com.onursumakoglu.cookrecipes.presentation.recipes

import android.content.Context
import android.view.Window
import com.onursumakoglu.cookrecipes.domain.entity.Recipe

class Singleton {
    companion object{
        private lateinit var recipeInfoDialog: RecipeInfoDialog

        fun showRecipeInfoDialog(context: Context, recipe: Recipe){
            recipeInfoDialog = RecipeInfoDialog(context, recipe)
            recipeInfoDialog.setCancelable(true)
            recipeInfoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            recipeInfoDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            recipeInfoDialog.show()

        }
    }
}