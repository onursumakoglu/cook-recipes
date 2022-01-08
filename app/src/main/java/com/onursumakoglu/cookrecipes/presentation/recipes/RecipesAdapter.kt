package com.onursumakoglu.cookrecipes.presentation.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.R
import com.onursumakoglu.cookrecipes.databinding.ItemGridRecipeBinding
import com.onursumakoglu.cookrecipes.databinding.ItemSingleRecipeBinding
import com.onursumakoglu.cookrecipes.domain.entity.Recipe

class RecipesAdapter(private var mList: List<Any>, var type: String) : RecyclerView.Adapter<RecipesAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapterViewHolder {
        when(viewType){
            R.layout.item_single_recipe -> {
                val binding = ItemSingleRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RecipesAdapterViewHolder.RecipesSingleViewHolder(binding)
            }
            R.layout.item_grid_recipe -> {
                val binding = ItemGridRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RecipesAdapterViewHolder.RecipesGridViewHolder(binding)
            }
        }

        val binding = ItemSingleRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesAdapterViewHolder.RecipesSingleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesAdapterViewHolder, position: Int) {
        when(holder){
            is RecipesAdapterViewHolder.RecipesSingleViewHolder -> {
                holder.singleItem.recipe = mList[position] as? Recipe
            }
            is RecipesAdapterViewHolder.RecipesGridViewHolder -> {
                holder.gridItem.recipe = mList[position] as? Recipe
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        when(type){
            "normal" -> return R.layout.item_single_recipe
            "grid" -> return R.layout.item_grid_recipe
        }

        return R.layout.item_single_recipe
    }

}