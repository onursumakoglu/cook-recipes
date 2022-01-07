package com.onursumakoglu.cookrecipes.presentation.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.R
import com.onursumakoglu.cookrecipes.databinding.DoubleRecipeListItemBinding
import com.onursumakoglu.cookrecipes.databinding.RecipeNormalListItemBinding
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import com.onursumakoglu.cookrecipes.presentation.common.RandomRecipeList

class RecipesAdapter(private var mList: List<Any>, var type: String) : RecyclerView.Adapter<RecipesAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapterViewHolder {

        when(viewType){
            R.layout.recipe_normal_list_item -> {
                val binding = RecipeNormalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RecipesAdapterViewHolder.SingleRecipeViewHolder(binding)
            }
            R.layout.double_recipe_list_item -> {
                val binding = DoubleRecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return RecipesAdapterViewHolder.GridRecipeViewHolder(binding)
            }
        }

        val binding = RecipeNormalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesAdapterViewHolder.SingleRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesAdapterViewHolder, position: Int) {

        when(holder){
            is RecipesAdapterViewHolder.SingleRecipeViewHolder -> {
                holder.singleItem.recipe = mList[position] as? Recipe
            }
            is RecipesAdapterViewHolder.GridRecipeViewHolder -> {
                val list = (mList[position] as RandomRecipeList).list

                holder.doubleItem.leftRecipe = if(list?.size ?: 0 > 0) list?.get(0) else null
                holder.doubleItem.rightRecipe = if(list?.size ?: 0 > 1) list?.get(1) else null
            }
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {

        when(type){
            "double" -> return R.layout.double_recipe_list_item
            "list" -> return R.layout.recipe_normal_list_item
        }

        return R.layout.recipe_normal_list_item

    }

}