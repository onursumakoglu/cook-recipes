package com.onursumakoglu.cookrecipes.presentation.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
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
        // Inflate the layout for this fragment //
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.viewModel = recipesViewModel

        lifecycleScope.launch {
            recipesViewModel.recipesState.collect {
                when(it){
                    is RecipesUiState.Success -> {
                        it.recipesEntity.randomRecipe?.let {

                            mList.addAll(it)
                            binding.recipesFragmentCookRecipeList.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
                            binding.recipesFragmentCookRecipeList.adapter = RecipesAdapter(it, "list")
                        }
                    }
                    is RecipesUiState.PageSuccess -> {
                        var beforeCount = mList.size

                        var randomList = createRecipeList(it.recipeList)

                        mList.addAll(randomList)

                        var endcount = mList.size

                        binding.recipesFragmentCookRecipeList.adapter?.notifyItemChanged(beforeCount, endcount)
                    }
                }
            }


        }

        recipesViewModel.getList()



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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}


        /*
        _binding?.viewModel = recipesViewModel

        lifecycleScope.launch {
            recipesViewModel.recipesState.collect{ recipesUiState ->
                when(recipesUiState){
                    is RecipesUiState.Success -> {

                        mList.addAll(createRecipeList(recipesUiState.recipesEntity.randomRecipe))

                        binding.recipesFragmentCookRecipeList.adapter = RecipesAdapter(mList)
                    }
                    is RecipesUiState.PageSuccess -> {
                        var beforeCount = mList.size

                        var randomList = createRecipeList(recipesUiState.recipeList)

                        mList.addAll(randomList)

                        var endcount = mList.size

                        binding.recipesFragmentCookRecipeList.adapter?.notifyItemChanged(beforeCount, endcount)
                    }
                }
            }
        }

        recipesViewModel.getList()

        addListener()

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

         */