package com.onursumakoglu.cookrecipes.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onursumakoglu.cookrecipes.domain.BaseResult
import com.onursumakoglu.cookrecipes.domain.entity.HomeEntity
import com.onursumakoglu.cookrecipes.domain.entity.Recipe
import com.onursumakoglu.cookrecipes.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase): ViewModel() {

    private val _uiState : MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Idle)
    val homeState: StateFlow<HomeUiState> = _uiState

    fun getList(){
        viewModelScope.launch {
            homeUseCase.execute()
                .onStart {
                    _uiState.value = HomeUiState.Loading
                }
                .catch { exception ->
                    _uiState.value = HomeUiState.Error(exception.message)
                }
                .collect { baseResult ->
                    when(baseResult){
                        is BaseResult.Success -> _uiState.value = HomeUiState.Success(baseResult.data)
                    }
                }
        }
    }

    fun fetch(){
        if (_uiState.value != HomeUiState.Loading){
            viewModelScope.launch {
                homeUseCase.nextRandomRecipe()
                    .onStart {
                        _uiState.value = HomeUiState.Loading
                    }
                    .catch { exception ->
                        _uiState.value = HomeUiState.Error(exception.message)
                    }
                    .collect { baseResult ->
                        when(baseResult){
                            is BaseResult.Success -> _uiState.value = HomeUiState.PageSuccess(baseResult.data)
                        }
                    }
            }
        }
    }

}

sealed class HomeUiState {
    data class Success(val homeEntity: HomeEntity): HomeUiState()
    data class Error(val error: String?): HomeUiState()
    data class PageSuccess(val recipeList: List<Recipe>): HomeUiState()
    object Idle : HomeUiState()
    object Loading : HomeUiState()
}