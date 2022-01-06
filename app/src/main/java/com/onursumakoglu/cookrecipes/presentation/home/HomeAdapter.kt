package com.onursumakoglu.cookrecipes.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.R
import com.onursumakoglu.cookrecipes.databinding.DoubleRecipeListItemBinding
import com.onursumakoglu.cookrecipes.databinding.TodayTopRecipeItemBinding
import com.onursumakoglu.cookrecipes.databinding.TodayTopRecipeItemStringBinding
import com.onursumakoglu.cookrecipes.domain.entity.Recipe

class HomeAdapter(private val mList: List<Any>) : RecyclerView.Adapter<HomeAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {

        when(viewType){
            R.layout.today_top_recipe_item_string -> {
                val binding = TodayTopRecipeItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeAdapterViewHolder.HomeTopRecipeTitleHolder(binding)
            }
            R.layout.today_top_recipe_item -> {
                val binding = TodayTopRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeAdapterViewHolder.HomeTopRecipeItemHolder(binding)
            }
            R.layout.double_recipe_list_item -> {
                val binding = DoubleRecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeAdapterViewHolder.DoubleRecipeItemHolder(binding)
            }

        }

        val binding = TodayTopRecipeItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeAdapterViewHolder.HomeTopRecipeTitleHolder(binding)

    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {

        when(holder){
            is HomeAdapterViewHolder.HomeTopRecipeTitleHolder -> {
                holder.topRecipeTitle.todayTopRecipeText.text = mList[position] as? String
            }
            is HomeAdapterViewHolder.HomeTopRecipeItemHolder -> {
                holder.topRecipeItem.recipe = mList[position] as? Recipe
            }
            is HomeAdapterViewHolder.DoubleRecipeItemHolder -> {
                val list = (mList[position] as RandomRecipeList).list

                holder.doubleRecipe.leftRecipe = if (list?.size ?: 0 > 0) list?.get(0) else null
                holder.doubleRecipe.rightRecipe = if (list?.size ?: 0 > 1) list?.get(1) else null
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {

        when(mList[position]){
            is String -> return R.layout.today_top_recipe_item_string
            is Recipe -> return R.layout.today_top_recipe_item
            is RandomRecipeList -> return R.layout.double_recipe_list_item
        }

        return R.layout.today_top_recipe_item_string
    }

}