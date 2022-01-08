package com.onursumakoglu.cookrecipes.presentation.recipes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.databinding.ItemGridRecipeBinding
import com.onursumakoglu.cookrecipes.databinding.ItemSingleRecipeBinding

sealed class RecipesAdapterViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {

    class RecipesSingleViewHolder(var singleItem: ItemSingleRecipeBinding) : RecipesAdapterViewHolder(singleItem.root)
    class RecipesGridViewHolder(var gridItem: ItemGridRecipeBinding) : RecipesAdapterViewHolder(gridItem.root)
}