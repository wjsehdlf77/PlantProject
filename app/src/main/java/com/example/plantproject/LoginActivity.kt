package com.example.plantproject

//import android.content.Intent
//import android.graphics.Color
//import android.os.Bundle
//import android.text.Spannable
//import android.text.SpannableStringBuilder
//import android.text.Spanned
//import android.text.method.LinkMovementMethod
//import android.text.style.ClickableSpan
//import android.text.style.ForegroundColorSpan
//import android.text.util.Linkify
//import android.text.util.Linkify.TransformFilter
//import android.view.View
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.android.volley.Response
//import com.android.volley.toolbox.Volley
//import com.example.plantproject.Login.LoginRequest
//import com.example.plantproject.databinding.ActivityLoginBinding
//import kotlinx.android.synthetic.main.activity_login.*
//import org.json.JSONObject
//import java.util.regex.Pattern
//
//
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.plantproject.DetailActivity.DetectActivity

import com.example.plantproject.Login.LoginService
import com.example.plantproject.databinding.ActivityLoginBinding
import com.example.plantproject.databinding.ActivityMainBinding

import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val spannable = SpannableStringBuilder("아이디가 없으시면 이곳에서 회원가입 해주시기 바랍니다.")
        binding.textView5.setText(spannable, TextView.BufferType.SPANNABLE)

        val spannableText = binding.textView5.text as Spannable

        spannableText.setSpan(object : ClickableSpan() {
            override fun onClick(p0: View) {
                IntentRegister()
            }
        }, 15, 20, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        binding.textView5.movementMethod = LinkMovementMethod.getInstance()




        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.4:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var loginService: LoginService = retrofit.create(LoginService::class.java)

        btn_login.setOnClickListener {
            var id = binding.editLoginId.text.toString()
            var pw = binding.editLoginPassword.text.toString()




            loginService.requestLogin(id, pw)?.enqueue(object : Callback<ResponseBody?> {
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                }
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "로그인이 됐습니다.", Toast.LENGTH_SHORT).show()
                        IntentMainActivity(id)


                    } else {
                        Toast.makeText(applicationContext, "아이디나 비밀번호가 틀렸습니다. 다시 입력해주세요.", Toast.LENGTH_SHORT).show()

                    }
                }
            })
        }
    }

    private fun IntentRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)

        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
    private fun IntentMainActivity(id: String) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.putExtra("Key_id", id)
        startActivity(intent)
        finish()
    }

    private fun IntentRegisterActivity(id: String) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}


//class LoginActivity : AppCompatActivity() {
//    val DATABASE_VERSION = 1
//    val DATABASE_NAME = "LocalDB.db"
//    private lateinit var binding: ActivityLoginBinding
////    private lateinit var localDB: LocalDB
//
//
//    //    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////
////
////    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        binding = ActivityLoginBinding.inflate(layoutInflater) // 뷰바인딩
//        val view = binding.root
//        setContentView(view)
//
////        val spannable = SpannableStringBuilder("아이디가 없으시면 회원가입을 해주시기 바랍니다.")
////
////        binding.textView5.setText(spannable, TextView.BufferType.SPANNABLE)
////        val spannableText = binding.textView5.text as Spannable
////        spannableText.setSpan(
////            ForegroundColorSpan(Color.rgb(129,193,71)),
////            9, 14,
////            Spannable.SPAN_INCLUSIVE_INCLUSIVE
////        )
//
//        val spannable = SpannableStringBuilder("아이디가 없으시면 이곳에서 회원가입 해주시기 바랍니다.")
//        binding.textView5.setText(spannable, TextView.BufferType.SPANNABLE)
//
//        val spannableText = binding.textView5.text as Spannable
//
//        spannableText.setSpan(object : ClickableSpan() {
//            override fun onClick(p0: View) {
//                IntentRegister()
//            }
//        }, 15, 20, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
//        binding.textView5.movementMethod = LinkMovementMethod.getInstance()
//
//
//
//
////        localDB= LocalDB(this, DATABASE_NAME,null, DATABASE_VERSION) // SQLite 모듈 생성
////        binding.btnLogin.setOnClickListener { view->		//	로그인 버튼 클릭 리스너
////            val id = binding.editId.text.toString()
////            val passwd = binding.editPw.text.toString()
////            val exist = localDB.logIn(id,passwd) // 로그인 실행
////            if(exist){//로그인 성공
////                val intent =Intent(this,MainActivity::class.java)
////                startActivity(intent)
////            }else{//실패
////                Toast.makeText(this@LoginActivity, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
////            }
////        }
//
//
//        binding.btnLogin.setOnClickListener {
//
//            if (binding.editLoginId.text.toString() == "admin"){
//                if (binding.editLoginPassword.text.toString() == "1234"){
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//            }
//
//
//            val responseListener: Response.Listener<String> =
//                Response.Listener { response ->
//                    try {
//                        val jsonResponse = JSONObject(response)
//                        val success = jsonResponse.getBoolean("success")
//                        if (success) {
//                            Toast.makeText(this, "로그인에 성공했습니다.", Toast.LENGTH_SHORT)
//                                .show()
//                            val userID = jsonResponse.getString("userID")
//                            val userPassword = jsonResponse.getString("userPassword")
//                            val intent = Intent(this, MainActivity::class.java)
//                            // 로그인 하면서 사용자 정보 넘기기
//                            intent.putExtra("userID", userID)
//                            intent.putExtra("userPassword", userPassword)
//                            startActivity(intent)
//                            finish()
//                        } else {
//                            Toast.makeText(this, "로그인에 실패했습니다.", Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//
//            val loginRequest = LoginRequest(binding.editLoginId.text.toString(), binding.editLoginPassword.text.toString(), responseListener)
//            val queue = Volley.newRequestQueue(this)
//            queue.add(loginRequest)
//        }
//    }
//
//    override fun onDestroy() {// 엑티비티가 종료시 close
//        super.onDestroy()
//
//
//    }
//
//}

