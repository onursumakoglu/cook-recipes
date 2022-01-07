package com.onursumakoglu.cookrecipes.presentation.recipes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.databinding.DoubleRecipeListItemBinding
import com.onursumakoglu.cookrecipes.databinding.RecipeNormalListItemBinding

sealed class RecipesAdapterViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {

    class SingleRecipeViewHolder(var singleItem: RecipeNormalListItemBinding) : RecipesAdapterViewHolder(singleItem.root)
    class GridRecipeViewHolder(var doubleItem: DoubleRecipeListItemBinding) : RecipesAdapterViewHolder(doubleItem.root)
}