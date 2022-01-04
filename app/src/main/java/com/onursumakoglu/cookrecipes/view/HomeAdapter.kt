package com.onursumakoglu.cookrecipes.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.R
import com.onursumakoglu.cookrecipes.databinding.DoubleRecipeListItemBinding
import com.onursumakoglu.cookrecipes.databinding.TodayTopRecipeItemBinding
import com.onursumakoglu.cookrecipes.databinding.TodayTopRecipeItemStringBinding
import com.onursumakoglu.cookrecipes.databinding.TopRecipesSeeallItemStringBinding

class HomeAdapter(private val mList: List<*>) : RecyclerView.Adapter<HomeAdapterViewHolder>(){

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
            R.layout.top_recipes_seeall_item_string -> {
                val binding = TopRecipesSeeallItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeAdapterViewHolder.TopSeeAllTitleHolder(binding)
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
        }


    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}