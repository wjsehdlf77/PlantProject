package com.example.plantproject.Login

import com.google.gson.annotations.SerializedName

data class GetHealth(
    @SerializedName("photoid") val id:String,
    @SerializedName("status") val health:String,
)
