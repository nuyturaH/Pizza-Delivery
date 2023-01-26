package com.harutyun.domain.usecase

import com.harutyun.domain.model.Pizza
import com.harutyun.domain.repository.PizzasRepository

class GetAddedPizzasUseCase(private val pizzasRepository: PizzasRepository) {

    operator fun invoke(): List<Pizza> {
        return pizzasRepository.getAddedPizzas()
    }
}