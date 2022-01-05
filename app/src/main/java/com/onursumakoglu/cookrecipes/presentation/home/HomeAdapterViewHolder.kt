package com.onursumakoglu.cookrecipes.presentation.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.databinding.DoubleRecipeListItemBinding
import com.onursumakoglu.cookrecipes.databinding.TodayTopRecipeItemBinding
import com.onursumakoglu.cookrecipes.databinding.TodayTopRecipeItemStringBinding
import com.onursumakoglu.cookrecipes.databinding.TopRecipesSeeallItemStringBinding

sealed class HomeAdapterViewHolder(val binding: View) : RecyclerView.ViewHolder(binding){

    class HomeTopRecipeTitleHolder(val topRecipeTitle: TodayTopRecipeItemStringBinding) : HomeAdapterViewHolder(topRecipeTitle.root)
    class HomeTopRecipeItemHolder(val topRecipeItem: TodayTopRecipeItemBinding) : HomeAdapterViewHolder(topRecipeItem.root)
    class TopSeeAllTitleHolder(val topSeeAllTitle: TopRecipesSeeallItemStringBinding) : HomeAdapterViewHolder(topSeeAllTitle.root)
    class DoubleRecipeItemHolder(val doubleRecipe: DoubleRecipeListItemBinding) : HomeAdapterViewHolder(doubleRecipe.root)

}