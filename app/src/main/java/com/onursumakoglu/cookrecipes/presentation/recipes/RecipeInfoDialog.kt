package com.onursumakoglu.cookrecipes.presentation.recipes

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.onursumakoglu.cookrecipes.R
import com.onursumakoglu.cookrecipes.databinding.DialogRecipeInfoBinding
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import com.onursumakoglu.cookrecipes.util.BindingUtil

class RecipeInfoDialog(mContext: Context, val recipe: Recipe): Dialog(mContext) {

    private lateinit var binding: DialogRecipeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogRecipeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dialogRecipeTitle.text = recipe.title
        BindingUtil.loadImage(binding.dialogRecipeImage, recipe.image)
        binding.dialogRecipePoint.text = recipe.spoonacularScore.toString()
        binding.dialogRecipeMinutes.text = recipe.readyInMinutes.toString()
        binding.dialogRecipeDescription.text = recipe.summary

    }

}