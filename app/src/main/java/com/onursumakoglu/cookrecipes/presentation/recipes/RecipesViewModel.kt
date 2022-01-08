package com.onursumakoglu.cookrecipes.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onursumakoglu.cookrecipes.domain.common.BaseResult
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import com.onursumakoglu.cookrecipes.domain.entity.RecipesEntity
import com.onursumakoglu.cookrecipes.domain.usecase.HomeUseCase
import com.onursumakoglu.cookrecipes.domain.usecase.RecipesUseCase
import com.onursumakoglu.cookrecipes.presentation.home.HomeUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipesViewModel(private val recipesUseCase: RecipesUseCase) : ViewModel() {

    private val _uiState : MutableStateFlow<RecipesUiState> = MutableStateFlow(RecipesUiState.Idle)
    val recipesState: StateFlow<RecipesUiState> = _uiState

    fun getList(){
        viewModelScope.launch {
            recipesUseCase.execute()
                .onStart {
                    _uiState.value = RecipesUiState.Loading
                }
                .catch { exception ->
                    _uiState.value = RecipesUiState.Error(exception.message)
                }
                .collect { baseResult ->
                    when(baseResult){
                        is BaseResult.Success -> _uiState.value = RecipesUiState.Success(baseResult.data)
                    }
                }
        }
    }

    fun fetch(){
        if (_uiState.value != RecipesUiState.Loading){
            viewModelScope.launch {
                recipesUseCase.nextRandomRecipe()
                    .onStart {
                        _uiState.value = RecipesUiState.Loading
                    }
                    .catch { exception ->
                        _uiState.value = RecipesUiState.Error(exception.message)
                    }
                    .collect { baseResult ->
                        when(baseResult){
                            is BaseResult.Success -> _uiState.value = RecipesUiState.PageSuccess(baseResult.data)
                        }
                    }
            }
        }
    }

}

sealed class RecipesUiState {
    data class Success(val recipesEntity: RecipesEntity): RecipesUiState()
    data class Error(val error: String?): RecipesUiState()
    data class PageSuccess(val recipeList: List<Recipe>): RecipesUiState()
    object Idle : RecipesUiState()
    object Loading : RecipesUiState()
}