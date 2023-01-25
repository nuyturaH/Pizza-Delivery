package com.harutyun.pizzadelivery.presentation.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harutyun.domain.usecase.GetPizzasUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val getPizzasUseCase: GetPizzasUseCase) :
    ViewModel() {


    private val _uiState = MutableStateFlow<MenuUiState>(MenuUiState.Loading)
    val uiState: StateFlow<MenuUiState> = _uiState.asStateFlow()


    init {
        getPizzas()
    }

    fun getPizzas() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _uiState.value = MenuUiState.Loading

                try {
                    val getPizzasUseCase = getPizzasUseCase()

                    if (getPizzasUseCase.isEmpty()) {
                        _uiState.value = MenuUiState.NoData
                    } else {
                        _uiState.value = MenuUiState.Success(getPizzasUseCase())
                    }
                } catch (e: Exception) {
                    _uiState.value = MenuUiState.Error(e.localizedMessage ?: "error")
                }
            }
        }
    }

}