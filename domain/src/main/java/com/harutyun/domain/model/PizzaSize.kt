package com.harutyun.domain.model

sealed class PizzaSize {
    object Full: PizzaSize()
    object Half: PizzaSize()
}
