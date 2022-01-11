package com.onursumakoglu.cookrecipes.domain.usecase

import com.onursumakoglu.cookrecipes.data.home.repository.CommonRepository
import com.onursumakoglu.cookrecipes.data.home.remote.dto.RandomRecipeResponse
import com.onursumakoglu.cookrecipes.domain.common.BaseResult
import com.onursumakoglu.cookrecipes.domain.entity.HomeEntity
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import kotlinx.coroutines.flow.flow

class HomeUseCase(private val commonRepository: CommonRepository) {

    var page = 0
    val pageSize = 20

    fun execute() : kotlinx.coroutines.flow.Flow<BaseResult<HomeEntity, RandomRecipeResponse>> {
        return flow {

            emit(
                BaseResult.Success(
                    HomeEntity(
                        todaysRecipe = commonRepository.getTodaysRecipe(),
                        randomRecipe = commonRepository.getRandomRecipes(0, pageSize)
                    )
                )
            )
        }
    }

    fun nextRandomRecipe() : kotlinx.coroutines.flow.Flow<BaseResult<List<Recipe>, RandomRecipeResponse>> {
        ++page
        return flow {

            emit(
                BaseResult.Success(
                    commonRepository.getRandomRecipes(page * pageSize, (page + 1) * pageSize)
                )
            )
        }
    }

}