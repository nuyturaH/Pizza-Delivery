package com.harutyun.data.remote

import com.harutyun.data.remote.entity.PizzaEntity

interface PizzasRemoteDataSource {

    suspend fun getPizzas() : List<PizzaEntity>
}