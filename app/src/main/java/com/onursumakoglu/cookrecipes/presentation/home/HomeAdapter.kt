package com.onursumakoglu.cookrecipes.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.R
import com.onursumakoglu.cookrecipes.databinding.ItemDoubleRecipeBinding
import com.onursumakoglu.cookrecipes.databinding.ItemHomeTitleBinding
import com.onursumakoglu.cookrecipes.databinding.ItemHomeTopRecipeBinding
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import com.onursumakoglu.cookrecipes.presentation.common.RandomRecipeList

class HomeAdapter(private val mList: List<Any>) : RecyclerView.Adapter<HomeAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {

        when(viewType){
            R.layout.item_home_title -> {
                val binding = ItemHomeTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeAdapterViewHolder.HomeTitleItemViewHolder(binding)
            }
            R.layout.item_home_top_recipe -> {
                val binding = ItemHomeTopRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeAdapterViewHolder.HomeTopRecipeItemHolder(binding)
            }
            R.layout.item_double_recipe -> {
                val binding = ItemDoubleRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeAdapterViewHolder.HomeDoubleRecipeItemHolder(binding)
            }
        }

        val binding = ItemHomeTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeAdapterViewHolder.HomeTitleItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {

        when(holder){
            is HomeAdapterViewHolder.HomeTitleItemViewHolder -> {
                holder.titleItem.todayTopRecipeText.text = mList[position] as? String
            }
            is HomeAdapterViewHolder.HomeTopRecipeItemHolder -> {
                holder.topRecipeItem.recipe = mList[position] as? Recipe
            }
            is HomeAdapterViewHolder.HomeDoubleRecipeItemHolder -> {
                val list = (mList[position] as RandomRecipeList).list

                holder.doubleRecipeItem.leftRecipe = if (list?.size ?: 0 > 0) list?.get(0) else null
                holder.doubleRecipeItem.rightRecipe = if (list?.size ?: 0 > 1) list?.get(1) else null
            }
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {

        when(mList[position]){
            is String -> return R.layout.item_home_title
            is Recipe -> return R.layout.item_home_top_recipe
            is RandomRecipeList -> return R.layout.item_double_recipe
        }

        return R.layout.item_home_title
    }
}