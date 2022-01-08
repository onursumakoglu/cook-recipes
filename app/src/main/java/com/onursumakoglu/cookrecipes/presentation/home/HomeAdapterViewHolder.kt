package com.onursumakoglu.cookrecipes.presentation.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.databinding.ItemDoubleRecipeBinding
import com.onursumakoglu.cookrecipes.databinding.ItemHomeTitleBinding
import com.onursumakoglu.cookrecipes.databinding.ItemHomeTopRecipeBinding

sealed class HomeAdapterViewHolder(binding: View) : RecyclerView.ViewHolder(binding){

    class HomeTitleItemViewHolder(val titleItem: ItemHomeTitleBinding) : HomeAdapterViewHolder(titleItem.root)
    class HomeTopRecipeItemHolder(val topRecipeItem: ItemHomeTopRecipeBinding) : HomeAdapterViewHolder(topRecipeItem.root)
    class HomeDoubleRecipeItemHolder(val doubleRecipeItem: ItemDoubleRecipeBinding) : HomeAdapterViewHolder(doubleRecipeItem.root)

}