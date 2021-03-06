package com.example.plantproject

//import android.content.Intent
//import android.graphics.Paint
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.isInvisible
//import com.android.volley.RequestQueue
//import com.android.volley.Response
//import com.android.volley.toolbox.Volley
//import com.example.plantproject.Login.RegisterIdCheck
//import com.example.plantproject.Login.RegisterRequest
//import com.example.plantproject.databinding.ActivityRegisterBinding
//import kotlinx.android.synthetic.main.activity_register.view.*
//import org.json.JSONObject
//
//

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.plantproject.DetailActivity.DetectActivity
import com.example.plantproject.Login.LoginService
import com.example.plantproject.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var isIdCheck = false

class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var loginService: LoginService = retrofit.create(LoginService::class.java)



        binding.btnidcheck.setOnClickListener {
            if (binding.editId.text.isNotEmpty()) {
                loginService.requestIdCheck(binding.editId.text.toString())
                    ?.enqueue(object : Callback<ResponseBody?> {
                        override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {

                        }

                        override fun onResponse(
                            call: Call<ResponseBody?>,
                            response: Response<ResponseBody?>
                        ) {
                            if (response.isSuccessful) {
                                isIdCheck = true
                                binding.checkSuccess.visibility = View.VISIBLE
                                binding.btnidcheck.visibility = View.INVISIBLE
                                binding.editId.isEnabled = false
                            } else {
                                Toast.makeText(baseContext, "???????????? ???????????????", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
            }else {
                Toast.makeText(baseContext,"??????????????? ??????????????????", Toast.LENGTH_SHORT).show()
            }
        }

        btn_register.setOnClickListener {

            if (binding.editId.text.toString().isEmpty() || binding.editPassword.text.toString().isEmpty() ||
                binding.editPasswordCheck.text.toString().isEmpty() || binding.editName.text.toString().isEmpty() ||
                binding.editBirth.text.toString().isEmpty()) {
                Toast.makeText(this, "?????? ?????? ???????????????", Toast.LENGTH_LONG).show()
            } else {
                if (isIdCheck) {
                    if (binding.editPassword.text.toString() == binding.editPasswordCheck.text.toString()) {
                        if (binding.agreedata.isChecked) {

                            registerAccount(binding.editId.text.toString(),binding.editPassword.text.toString(),
                                binding.editName.text.toString(),binding.editBirth.text.toString())

                        } else {
                            Toast.makeText(this, "???????????? ????????? ??????????????????", Toast.LENGTH_LONG).show()
                    }
                    }else{
                        Toast.makeText(this, "??????????????? ?????? ????????????.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(baseContext, "????????? ?????? ????????? ????????????", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



    private fun registerAccount(userId: String, userPass: String, userName: String, userBirth:String) {
        val identify = RequestBody.create(MediaType.parse("text/plain"), userId)
        val password = RequestBody.create(MediaType.parse("text/plain"), userPass)
        val name = RequestBody.create(MediaType.parse("text/plain"), userName)
        val birth = RequestBody.create(MediaType.parse("text/plain"), userBirth)


        // Retrofit ?????? ??????
        val builder2 = Retrofit.Builder()
            .baseUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000")

            .addConverterFactory(GsonConverterFactory.create())
        val retrofit2 = builder2.build()
        val myAPI2: LoginService = retrofit2.create(LoginService::class.java)

        myAPI2.register(identify, password,name, birth)?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext,
                        "?????? ????????? ?????????????????????!",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(baseContext, DetectActivity::class.java)
                    intent.putExtra("register_id", userId)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(baseContext,
                        "????????? ????????????",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
            }
        })
    }

}
//var isExistBlank = false
//var isPWSame = false
//var isExist = false
//var isDataCheck = false
//var isIdCheck = false
//
//class RegisterActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityRegisterBinding
//    private lateinit var localDB: LocalDB
//    val DATABASE_VERSION = 1
//    val DATABASE_NAME = "LocalDB.db"
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRegisterBinding.inflate(layoutInflater)    // ??? ?????????
//        val view = binding.root
//        setContentView(view)
//
//        binding.agreedata.setOnCheckedChangeListener { compoundButton, isChecked ->
//            isDataCheck = isChecked
//        }
//
//
////        localDB = LocalDB(this, DATABASE_NAME, null, DATABASE_VERSION) // SQLite ?????? ??????
//
//        binding.btnidcheck.setOnClickListener {
//            if (binding.editId.text.isNotEmpty()) {
//                idcheck()
//
//            } else {
//                Toast.makeText(this, "???????????? ??????????????????", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        binding.btnRegister.setOnClickListener {
//
//            if (binding.editId.text.isEmpty() || binding.editPassword.text.isEmpty() ||
//                binding.editPasswordCheck.text.isEmpty() || binding.editName.text.isEmpty() ||
//                binding.editBirth.text.isEmpty()) {
//                Toast.makeText(this, "?????? ?????? ??????????????????..", Toast.LENGTH_LONG).show()
//            } else {
//                if(isIdCheck) {
//                    if (binding.editPassword.text.toString()
//                            .equals(binding.editPasswordCheck.text.toString())
//                    ) {
//                        if (isDataCheck) {
//                            register()
//                        } else {
//                            Toast.makeText(this, "????????????????????? ?????????????????????", Toast.LENGTH_SHORT).show()
//                        }
//
//                    } else {
//                        Toast.makeText(this, "??????????????? ???????????????.", Toast.LENGTH_LONG).show()
//                    }
//                }else {
//                    Toast.makeText(this, "???????????????????????? ????????????", Toast.LENGTH_SHORT).show()
//                }
//            }


//                val responseLisener = Response.Listener<String> { response ->
//
//                    try {
//                        Log.w("asd", response)
//                        val jsonResponse: JSONObject = JSONObject(response)
////                        val stringResponse = jsonResponse as String
//                        val success: Boolean = jsonResponse.getBoolean("success")
//                        if (success) { // ??????????????? ???????????????
//                            Toast.makeText(this, "??????????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
//
//                            val intent = Intent(this, LoginActivity::class.java)
//                            startActivity(intent)
//                            finish()
//
//                        } else {// ??????????????? ????????????
//                            Toast.makeText(
//                                this,
//                                "??????????????? ??????????????????. ?????? ??? ??? ????????? ?????????.",
//                                Toast.LENGTH_SHORT
//                            )
//                                .show();
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//
//                }
//
//                val registerRequest = RegisterRequest(
//                    binding.editId.text.toString(),
//                    binding.editPw.text.toString(),
//                    binding.editName.text.toString(),
//                    binding.editBirth.text.toString(),
//                    responseLisener
//                )
//                val queue = Volley.newRequestQueue(this)
//                queue.add(registerRequest)



//            if (binding.editId.text.isEmpty() || binding.editPw.text.isEmpty() || binding.editPwRe.text.isEmpty()) {
//                Toast.makeText(this, "?????? ?????? ??????????????????..", Toast.LENGTH_LONG).show()
//            } else {
//                if (binding.editPw.text.toString()
//                        .equals(binding.editPwRe.text.toString())
//                ) {//????????????/???????????? ????????? ??????
//                    if (localDB.checkIdExist(binding.editId.text.toString())) {// ????????? ?????? ??????
//                        Toast.makeText(this, "???????????? ?????? ???????????????.", Toast.LENGTH_LONG).show()
//                        isExist = false
//                    } else {// ???????????? ?????????
//                        localDB.registerUser(
//                            binding.editId.text.toString(),
//                            binding.editPw.text.toString()
//                        )
//                        isExist = true
//                    }
//                } else { // ????????????/???????????? ????????? ???????????? ??????
//                    Toast.makeText(this, "??????????????? ???????????????.", Toast.LENGTH_LONG).show()
//                }
//            }
//            if (binding.editId.text.isEmpty() || binding.editPw.text.isEmpty() || binding.editPwRe.text.isEmpty()) {
//                isExistBlank = true
//            } else {
//                if (binding.editPw.text.toString() == binding.editPwRe.text.toString()) {
//                    isPWSame = true
//                }
//            }
//            if (!isExistBlank && isPWSame && isExist) {
//                Toast.makeText(this, "??????????????? ?????????????????????..", Toast.LENGTH_LONG).show()
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
//
//    }
// ############????????????????????????
//    private fun register() {
//        val responseLisener = Response.Listener<String> { response ->
//
//            try {
//                Log.w("asd", response)
//                val jsonResponse: JSONObject = JSONObject(response)
////                        val stringResponse = jsonResponse as String
//                val success: Boolean = jsonResponse.getBoolean("success")
//                if (success) { // ??????????????? ???????????????
//                    Toast.makeText(this, "??????????????? ?????????????????????.", Toast.LENGTH_SHORT).show()
//
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)
//                    finish()
//
//                } else {// ??????????????? ????????????
//                    Toast.makeText(
//                        this,
//                        "??????????????? ??????????????????. ?????? ??? ??? ????????? ?????????.",
//                        Toast.LENGTH_SHORT
//                    )
//                        .show();
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
//
//        val registerRequest = RegisterRequest(
//            binding.editId.text.toString(),
//            binding.editPassword.text.toString(),
//            binding.editName.text.toString(),
//            binding.editBirth.text.toString(),
//            responseLisener
//        )
//        val queue = Volley.newRequestQueue(this)
//        queue.add(registerRequest)
//    }
//
//    private fun idcheck() {
//        val responseLisener = Response.Listener<String> { response ->
//            try {
//                val jsonResponse = JSONObject(response)
//                val success = jsonResponse.getBoolean("success")
//                isIdCheck = success
//                    if (success) {
//                        binding.checkSuccess.visibility = View.VISIBLE
//                        binding.btnidcheck.visibility = View.INVISIBLE
//                        binding.editId.isEnabled = false
//
//                    } else {
//                        Toast.makeText(this, "???????????? ???????????????", Toast.LENGTH_SHORT).show()
//                    }
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
//
//        val registerRequest = RegisterIdCheck(
//            binding.editId.text.toString(),
//            responseLisener
//        )
//        val queue = Volley.newRequestQueue(this)
//        queue.add(registerRequest)
//    }
//}







