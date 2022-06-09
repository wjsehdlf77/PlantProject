package com.example.plantproject.Login

import retrofit2.Call
import retrofit2.http.GET

interface SensorDown {

    @GET("raspberry")
    fun getSensor(): Call<List<Post>>
}