package com.example.plantproject.Login

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ImageUpload {

    @Multipart
    @POST("/profileimage/")
    fun requestBitmap(
        @Part("user") user: RequestBody?,
        @Part userimage : MultipartBody.Part
    ): Call<ResponseBody?>

    @GET("/~/img/and/{userid}.jpeg/")
    fun userProfileImage(
        @Path("userid") id:String
    ) : Call<ResponseBody>

    @GET("/profileimage/{userid}")
    fun userPlantName(
        @Path("userid") id:String

    ) : Call<GetLabel>
}