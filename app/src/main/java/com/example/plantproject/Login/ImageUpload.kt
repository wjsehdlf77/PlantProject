package com.example.plantproject.Login

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageUpload {

    @Multipart
    @POST("/profileimage/")
    fun requestBitmap(
        @Part("user") user: RequestBody?,
        @Part userImage : MultipartBody.Part
    ): Call<ResponseBody?>
}