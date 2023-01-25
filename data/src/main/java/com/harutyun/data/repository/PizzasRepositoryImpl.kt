package com.harutyun.data.repository

import com.harutyun.data.mapper.PizzaMapper
import com.harutyun.data.remote.PizzasRemoteDataSource
import com.harutyun.domain.model.Pizza
import com.harutyun.domain.repository.PizzasRepository

class PizzasRepositoryImpl(
    private val pizzasRemoteDataSource: PizzasRemoteDataSource,
    private val pizzaMapper: PizzaMapper
) : PizzasRepository {

    override suspend fun getPizzas(): List<Pizza> {
        return pizzaMapper.mapListToDomain(pizzasRemoteDataSource.getPizzas())
    }
}