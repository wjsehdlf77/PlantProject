package com.example.plantproject.Login

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface GetData {

    @GET("raspberry")
    fun getSensor(): Call<List<Post>>

    @GET("register/{userid}/")
    fun getUserData(
        @Path("userid") userid:String
    ) : Call<GetUser>

    @FormUrlEncoded
    @PUT("register/{userid}/")
    fun getPutField(
        @Path("userid") userid: String,
        @Field("userid") reuserid: String,
        @Field("userpassword") userpassword: String,
        @Field("username") username: String,
        @Field("userbirth") userbirth: String
    ): Call<ResponseBody>
}