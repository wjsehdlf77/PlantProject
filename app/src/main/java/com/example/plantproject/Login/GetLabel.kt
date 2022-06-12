package com.example.plantproject.Login

import com.google.gson.annotations.SerializedName

data class GetLabel (
    @SerializedName("user") val user:String,
    @SerializedName("plantname") val plantname:String,
)