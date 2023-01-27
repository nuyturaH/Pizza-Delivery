package com.harutyun.domain.model

import java.math.BigDecimal

data class Pizza(
    val name: String,
    val price: BigDecimal,
    var pizzaSize: PizzaSize = PizzaSize.Full,
    var isAdded: Boolean = false
) {

    val priceWithSymbol = "$${price.stripTrailingZeros().toPlainString()}"
    val realPrice: BigDecimal
        get() {
            return when (pizzaSize) {
                PizzaSize.Full -> price
                PizzaSize.Half -> price.divide(2.toBigDecimal())
            }
        }
    val realPriceWithSymbol = "$${realPrice.stripTrailingZeros().toPlainString()}"
}

