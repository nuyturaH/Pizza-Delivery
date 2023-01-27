package com.harutyun.pizzadelivery.presentation.ordersuccessful

import androidx.lifecycle.ViewModel
import com.harutyun.domain.usecase.GetAddedPizzasUseCase
import com.harutyun.domain.usecase.GetTotalPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OrderSuccessfulViewModel @Inject constructor(
    getAddedPizzasUseCase: GetAddedPizzasUseCase,
    getTotalPriceUseCase: GetTotalPriceUseCase
) :
    ViewModel() {

    private val _uiState = MutableStateFlow(OrderSuccessfulUiState())
    val uiState: StateFlow<OrderSuccessfulUiState> = _uiState.asStateFlow()

    init {
        val pizzaStringList =
            getAddedPizzasUseCase().map { "– ${it.name} (${it.pizzaSize.name}) – ${it.realPriceWithSymbol}" }
        val summery = pizzaStringList.joinToString(separator = "\n")
        val totalPrice = getTotalPriceUseCase(getAddedPizzasUseCase())

        _uiState.update { it.copy(summery = summery, totalPrice = totalPrice) }
    }
}