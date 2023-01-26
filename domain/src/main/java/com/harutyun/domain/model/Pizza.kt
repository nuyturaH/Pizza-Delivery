package com.harutyun.domain.model

data class Pizza(
    val name: String,
    val price: Double,
    var pizzaSize: PizzaSize = PizzaSize.Full,
    var isAdded: Boolean = false
) {

    val formattedPrice = "$$price"
    val realPrice: Double
        get() {
            return when (pizzaSize) {
                PizzaSize.Full -> price
                PizzaSize.Half -> price / 2
            }
        }
}

