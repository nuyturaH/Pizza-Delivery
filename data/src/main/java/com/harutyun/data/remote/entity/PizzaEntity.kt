package com.harutyun.data.remote.entity

import com.squareup.moshi.Json

data class PizzaEntity (

    @Json(name = "name")
    val name  : String,

    @Json(name = "price")
    val price : Int
)