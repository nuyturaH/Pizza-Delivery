package com.harutyun.domain.usecase

import com.harutyun.domain.model.Pizza

class GetTotalPriceUseCase {
    operator fun invoke(pizzas: List<Pizza>): Double {
        return pizzas.sumOf { it.realPrice }
    }
}