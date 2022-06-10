package com.example.plantproject.Login

import com.google.gson.annotations.SerializedName

data class GetUser(
    @SerializedName("userid") val userid:String,
    @SerializedName("userpassword") val userpassword:String,
    @SerializedName("username") val username:String,
    @SerializedName("userbirth") val userbirth:String,

)