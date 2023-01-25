package com.harutyun.data.remote

import com.harutyun.data.remote.entity.PizzaEntity
import retrofit2.http.GET

interface PizzasApi {

    @GET("tests/pizzas.json")
    suspend fun getPizzas(): List<PizzaEntity>
}