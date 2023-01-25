package com.harutyun.domain.repository

import com.harutyun.domain.model.Pizza

interface PizzasRepository {
    suspend fun getPizzas(): List<Pizza>
}