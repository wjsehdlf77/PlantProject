package com.example.plantproject.Login

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface LoginService {


    @Multipart
    @POST("/register/")
    fun register(
        @Part("userid") param1: RequestBody?,
        @Part("userpassword") param2: RequestBody?,
        @Part("username") param3: RequestBody?,
        @Part("userbirth") param4: RequestBody?
    ): Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("/login/")
    fun requestLogin(
        @Field("id") id:String,
        @Field("pw") pw:String
    ) : Call<ResponseBody?>?

    @FormUrlEncoded
    @POST("/checkid/")
    fun requestIdCheck(
        @Field("idcheck") checkid :String
    ) : Call<ResponseBody?>?
}