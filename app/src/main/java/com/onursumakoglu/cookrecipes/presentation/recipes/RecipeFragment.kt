package com.onursumakoglu.cookrecipes.presentation.recipes

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.R
import com.onursumakoglu.cookrecipes.databinding.FragmentRecipeBinding
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import com.onursumakoglu.cookrecipes.presentation.common.RandomRecipeList
import com.onursumakoglu.cookrecipes.presentation.home.HomeAdapter
import com.onursumakoglu.cookrecipes.presentation.home.HomeUiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get

class RecipeFragment : Fragment() {

    private val recipesViewModel: RecipesViewModel = get()
    private var mList = mutableListOf<Any>()

    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.viewModel = recipesViewModel

        lifecycleScope.launch {
            recipesViewModel.recipesState.collect { recipesUiState ->
                when(recipesUiState){
                    is RecipesUiState.Success -> {
                        recipesUiState.recipesEntity.randomRecipe.let { recipeList ->
                            mList.addAll(recipeList)
                           }

                        println(mList)

                        binding.recipesFragmentCookRecipeList.layoutManager = LinearLayoutManager(context)
                        binding.recipesFragmentCookRecipeList.adapter = RecipesAdapter(mList, "normal")
                    }
                    is RecipesUiState.PageSuccess -> {
                        val beforeCount = mList.size

                        recipesUiState.recipeList.randomRecipe.let { recipeList ->
                            mList.addAll(recipeList)
                        }

                        val endcount = mList.size

                        println(mList)
                        println(mList.size)

                        binding.recipesFragmentCookRecipeList.adapter?.let {
                            println("girdik")
                            it.notifyItemChanged(beforeCount, endcount)
                        }
                    }
                }
            }
        }

        recipesViewModel.getList()

        addListener()

        selectListType()

        backToHome()

        return root
    }

    fun createRecipeList(list: List<Recipe>): MutableList<RandomRecipeList>{
        var randomRecipeList = mutableListOf<RandomRecipeList>()

        list.forEachIndexed { index, recipe ->
            if (index %2 == 0)
                randomRecipeList.add(RandomRecipeList(mutableListOf(recipe)))
            else
                randomRecipeList[randomRecipeList.size-1].list?.add(recipe)
        }

        return randomRecipeList
    }

    fun addListener() {
        binding.recipesFragmentCookRecipeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
                    recipesViewModel.fetch()
                }
            }
        })
    }

    fun selectListType(){

        binding.gridIcon.setOnClickListener {
            binding.recipesFragmentCookRecipeList.layoutManager = GridLayoutManager(context, 2, )
            binding.recipesFragmentCookRecipeList.adapter = RecipesAdapter(mList, "grid")
            binding.gridIcon.setImageResource(R.drawable.ic_grid_menu_green)
            binding.listIcon.setImageResource(R.drawable.ic_list_menu_gray)
        }
        binding.listIcon.setOnClickListener {
            binding.recipesFragmentCookRecipeList.layoutManager = LinearLayoutManager(context)
            binding.recipesFragmentCookRecipeList.adapter = RecipesAdapter(mList, "normal")
            binding.listIcon.setImageResource(R.drawable.ic_list_menu)
            binding.gridIcon.setImageResource(R.drawable.ic_grid_menu)

        }
    }

    fun backToHome(){
        binding.recipesFragmentBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}