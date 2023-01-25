package com.harutyun.domain.usecase

import com.harutyun.domain.model.Pizza
import com.harutyun.domain.repository.PizzasRepository

class GetPizzasUseCase(private val pizzasRepository: PizzasRepository) {

    suspend operator fun invoke(): List<Pizza> {
        return pizzasRepository.getPizzas()
    }
}