package com.harutyun.domain.repository

import com.harutyun.domain.model.Pizza

interface PizzasRepository {
    suspend fun getPizzas(): List<Pizza>

    fun getAddedPizzas(): List<Pizza>

    fun saveAddedPizzas(pizzas: List<Pizza>)
}