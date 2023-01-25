package com.harutyun.data.mapper

import com.harutyun.data.remote.entity.PizzaEntity
import com.harutyun.domain.model.Pizza

class PizzaMapper {

    fun mapListToDomain(input: List<PizzaEntity>): List<Pizza> {
        return input.map { mapToDomain(it) }
    }

    fun mapToDomain(input: PizzaEntity): Pizza {
        return Pizza(
            name = input.name,
            price = input.price,
        )
    }
}