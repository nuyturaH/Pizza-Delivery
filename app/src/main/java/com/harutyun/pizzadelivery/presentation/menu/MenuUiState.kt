package com.harutyun.pizzadelivery.presentation.menu

import androidx.annotation.StringRes
import com.harutyun.domain.model.Pizza

sealed class MenuUiState {

    data class Success(
        val pizzas: List<Pizza> = listOf(),
        val isConfirmButtonVisible: Boolean = false,
        @StringRes val snackBarMessage: Int? = null
    ) : MenuUiState()

    object Loading : MenuUiState()
    object NoData : MenuUiState()
    data class Error(val errorMessage: String) : MenuUiState()

}
