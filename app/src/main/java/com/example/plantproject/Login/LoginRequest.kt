package com.example.plantproject.Login

import com.android.volley.AuthFailureError

import com.android.volley.Response

import com.android.volley.toolbox.StringRequest


class LoginRequest(userID: String, userPassword: String, listener: Response.Listener<String>) :
    StringRequest(Method.POST, URL, listener, null) {

    private val parameters: MutableMap<String, String>

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return parameters
    }

    companion object {
        private const val URL = "http://192.168.0.4/login.php" // "http:// 퍼블릭 DSN 주소/Login.php";
    }

    init {
        parameters = HashMap()
        parameters["userID"] = userID
        parameters["userPassword"] = userPassword
    }
}