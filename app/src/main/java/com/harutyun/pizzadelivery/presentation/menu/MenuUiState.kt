package com.harutyun.pizzadelivery.presentation.menu

import com.harutyun.domain.model.Pizza

sealed class MenuUiState {

    data class Success(
        val pizzas: List<Pizza> = listOf(),
        val isConfirmButtonVisible: Boolean = false
    ) : MenuUiState()

    object Loading : MenuUiState()
    object Refreshing : MenuUiState()
    object NoData : MenuUiState()
    data class Error(val errorMessage: String) : MenuUiState()

}
