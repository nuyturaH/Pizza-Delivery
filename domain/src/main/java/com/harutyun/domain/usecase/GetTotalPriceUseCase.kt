package com.harutyun.domain.usecase

import com.harutyun.domain.model.Pizza
import java.math.BigDecimal

class GetTotalPriceUseCase {
    operator fun invoke(pizzas: List<Pizza>): BigDecimal {
        return pizzas.fold(BigDecimal.ZERO) { acc, pizza -> acc + pizza.realPrice }
    }
}