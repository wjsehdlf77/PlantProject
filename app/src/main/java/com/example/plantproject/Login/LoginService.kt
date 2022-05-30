package com.example.plantproject.Login

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface LoginService {


    @Multipart
    @POST("/register/")
    fun register(
        @Part("id") param1: RequestBody?,
        @Part("pw") param2: RequestBody?,
        @Part("name") param3: RequestBody?,
        @Part("birth") param4: RequestBody?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("/login/")
    fun requestLogin(
        @Field("id") id:String,
        @Field("pw") pw:String
    ) : Call<ResponseBody?>?
}