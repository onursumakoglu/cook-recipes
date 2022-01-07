package com.onursumakoglu.cookrecipes.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.onursumakoglu.cookrecipes.databinding.FragmentHomeBinding
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import com.onursumakoglu.cookrecipes.presentation.common.RandomRecipeList
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel = get()
    private val mList = mutableListOf<Any>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding?.viewModel = homeViewModel

        lifecycleScope.launch {
            homeViewModel.homeState.collect{ homeUiState ->
                when(homeUiState){
                    is HomeUiState.Success -> {
                        homeUiState.homeEntity.todaysRecipe?.let { recipe ->
                            mList.add("Today's Top Pick")
                            mList.add(recipe)
                        }

                        mList.addAll(createRecipe(homeUiState.homeEntity.randomRecipe))

                        binding.cookRecipeList.adapter = HomeAdapter(mList)
                    }
                    is HomeUiState.PageSuccess -> {
                        var beforeCount = mList.size

                        var randomList = createRecipeList(homeUiState.recipeList)

                        mList.addAll(randomList)

                        var endcount = mList.size

                        binding.cookRecipeList.adapter?.notifyItemChanged(beforeCount, endcount)
                    }
                }
            }
        }

        homeViewModel.getList()

        addListener()

        return root
    }

    fun createRecipe(randomRecipe: List<Recipe>): List<Any>{
        var list = mutableListOf<Any>()
        if (randomRecipe.isNotEmpty()){
            list.add("Top Recipe")

            var randomList = mutableListOf<RandomRecipeList>()
            randomRecipe.forEachIndexed{ index, recipe ->
                if (index %2 == 0)
                    randomList.add(RandomRecipeList(mutableListOf(recipe)))
                else
                    randomList[randomList.size-1].list?.add(recipe)
            }

            list.addAll(randomList)
        }

        return list
    }

    fun createRecipeList(list: List<Recipe>): MutableList<RandomRecipeList>{
        var randomList = mutableListOf<RandomRecipeList>()

        list.forEachIndexed { index, recipe ->
            if (index %2 == 0)
                randomList.add(RandomRecipeList(mutableListOf(recipe)))
            else
                randomList[randomList.size-1].list?.add(recipe)
        }

        return randomList
    }

    fun addListener() {
        binding.cookRecipeList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)){
                    homeViewModel.fetch()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}