package com.harutyun.data.remote

import com.harutyun.data.remote.entity.PizzaEntity

class PizzasApiPizzasRemoteDataSource(private val pizzasApi: PizzasApi) : PizzasRemoteDataSource {

    override suspend fun getPizzas(): List<PizzaEntity> {
        return pizzasApi.getExchangeRates()
    }
}