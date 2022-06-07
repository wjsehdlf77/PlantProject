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

    @FormUrlEncoded
    @POST("/userprofileimage/media/")
    fun userProfileImage(
        @Field("userid") id:String
    ) : Call<ResponseBody?>?
}