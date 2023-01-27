package com.harutyun.pizzadelivery.presentation.ordersuccessful

import java.math.BigDecimal

data class OrderSuccessfulUiState(
    val summery: String? = null,
    val totalPrice: BigDecimal = BigDecimal.ZERO
)