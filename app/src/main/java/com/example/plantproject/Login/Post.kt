package com.example.plantproject.Login

import com.google.gson.annotations.SerializedName
import java.util.*

data class Post(

//    @SerializedName("id") val num:Int,
    @SerializedName("temp") val temp:Float,
    @SerializedName("humid") val humid:Float,
    @SerializedName("soil_hum") val soil_hum:Float,
    @SerializedName("light") val light:Float,
//    @SerializedName("data") val date:Date
)




