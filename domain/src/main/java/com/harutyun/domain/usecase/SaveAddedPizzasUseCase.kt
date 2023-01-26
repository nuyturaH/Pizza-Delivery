package com.harutyun.domain.usecase

import com.harutyun.domain.model.Pizza
import com.harutyun.domain.repository.PizzasRepository

class SaveAddedPizzasUseCase(private val pizzasRepository: PizzasRepository) {

    operator fun invoke(pizzas: List<Pizza>) {
        return pizzasRepository.saveAddedPizzas(pizzas)
    }
}