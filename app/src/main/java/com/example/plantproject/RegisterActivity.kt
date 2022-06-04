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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnidcheck.setOnClickListener {
            Toast.makeText(this,"값을 모두 채워주세요",Toast.LENGTH_LONG).show()
        }

        btn_register.setOnClickListener {
            var id = editId.text.toString()
            var pw = editPassword.text.toString()
            var pw_re = editPasswordCheck.text.toString()
            var name = editName.text.toString()
            var birth = editBirth.text.toString()


            if(id.isEmpty() || pw.isEmpty() || pw_re.isEmpty() || name.isEmpty() || birth.isEmpty())
                Toast.makeText(this,"값을 모두 채워주세요",Toast.LENGTH_LONG).show()
                if (pw == pw_re)
                    if(agreedata.isChecked)
                        registerAccount(id,pw,name,birth)
                    else
                        Toast.makeText(this,"회원정보 수집에 동의해주세요",Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this,"비밀번호가 같지 않습니다.",Toast.LENGTH_LONG).show()
        }
    }

    private fun registerAccount(userId: String, userPass: String, userName: String, userBirth:String) {
        val identify = RequestBody.create(MediaType.parse("text/plain"), userId)
        val password = RequestBody.create(MediaType.parse("text/plain"), userPass)
        val name = RequestBody.create(MediaType.parse("text/plain"), userName)
        val birth = RequestBody.create(MediaType.parse("text/plain"), userBirth)


        // Retrofit 객체 생성
        val builder2 = Retrofit.Builder()
            .baseUrl("http://172.30.1.14:8000")
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit2 = builder2.build()
        val myAPI2: LoginService = retrofit2.create(LoginService::class.java)

        // post 한다는 request를 보내는 부분.
        // 만약 서버로 부터 response를 받는다면.
        myAPI2.register(identify, password,name, birth)?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "계정 등록")
                    Toast.makeText(this@RegisterActivity,
                        "계정 등록에 성공하였습니다!",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("body", "${response.body()}")
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                   Toast.makeText(this@RegisterActivity,
                        "아이디가 중복됩니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d(TAG, "Post Status Code : " + response.code())
                    Log.d(TAG, response.errorBody().toString())
                    Log.d(TAG, call.request().body().toString())
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Log.d(TAG, "Fail msg : " + t.message)
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
//        binding = ActivityRegisterBinding.inflate(layoutInflater)    // 뷰 바인딩
//        val view = binding.root
//        setContentView(view)
//
//        binding.agreedata.setOnCheckedChangeListener { compoundButton, isChecked ->
//            isDataCheck = isChecked
//        }
//
//
////        localDB = LocalDB(this, DATABASE_NAME, null, DATABASE_VERSION) // SQLite 모듈 생성
//
//        binding.btnidcheck.setOnClickListener {
//            if (binding.editId.text.isNotEmpty()) {
//                idcheck()
//
//            } else {
//                Toast.makeText(this, "아이디가 비어있습니다", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        binding.btnRegister.setOnClickListener {
//
//            if (binding.editId.text.isEmpty() || binding.editPassword.text.isEmpty() ||
//                binding.editPasswordCheck.text.isEmpty() || binding.editName.text.isEmpty() ||
//                binding.editBirth.text.isEmpty()) {
//                Toast.makeText(this, "값을 전부 입력해주세요..", Toast.LENGTH_LONG).show()
//            } else {
//                if(isIdCheck) {
//                    if (binding.editPassword.text.toString()
//                            .equals(binding.editPasswordCheck.text.toString())
//                    ) {
//                        if (isDataCheck) {
//                            register()
//                        } else {
//                            Toast.makeText(this, "개인정보수집에 동의해주십시오", Toast.LENGTH_SHORT).show()
//                        }
//
//                    } else {
//                        Toast.makeText(this, "패스워드가 틀렸습니다.", Toast.LENGTH_LONG).show()
//                    }
//                }else {
//                    Toast.makeText(this, "아이디중복확인을 해주세요", Toast.LENGTH_SHORT).show()
//                }
//            }


//                val responseLisener = Response.Listener<String> { response ->
//
//                    try {
//                        Log.w("asd", response)
//                        val jsonResponse: JSONObject = JSONObject(response)
////                        val stringResponse = jsonResponse as String
//                        val success: Boolean = jsonResponse.getBoolean("success")
//                        if (success) { // 회원가입이 가능한다면
//                            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
//
//                            val intent = Intent(this, LoginActivity::class.java)
//                            startActivity(intent)
//                            finish()
//
//                        } else {// 회원가입이 안된다면
//                            Toast.makeText(
//                                this,
//                                "회원가입에 실패했습니다. 다시 한 번 확인해 주세요.",
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
//                Toast.makeText(this, "값을 전부 입력해주세요..", Toast.LENGTH_LONG).show()
//            } else {
//                if (binding.editPw.text.toString()
//                        .equals(binding.editPwRe.text.toString())
//                ) {//패스워드/패스워드 확인이 일치
//                    if (localDB.checkIdExist(binding.editId.text.toString())) {// 아이디 중복 확인
//                        Toast.makeText(this, "아이디가 이미 존재합니다.", Toast.LENGTH_LONG).show()
//                        isExist = false
//                    } else {// 존재하는 아이디
//                        localDB.registerUser(
//                            binding.editId.text.toString(),
//                            binding.editPw.text.toString()
//                        )
//                        isExist = true
//                    }
//                } else { // 패스워드/패스워드 확인이 일치하지 않음
//                    Toast.makeText(this, "패스워드가 틀렸습니다.", Toast.LENGTH_LONG).show()
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
//                Toast.makeText(this, "회원가입에 성공하셨습니다..", Toast.LENGTH_LONG).show()
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
//
//    }
// ############도일님이하신코드
//    private fun register() {
//        val responseLisener = Response.Listener<String> { response ->
//
//            try {
//                Log.w("asd", response)
//                val jsonResponse: JSONObject = JSONObject(response)
////                        val stringResponse = jsonResponse as String
//                val success: Boolean = jsonResponse.getBoolean("success")
//                if (success) { // 회원가입이 가능한다면
//                    Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
//
//                    val intent = Intent(this, LoginActivity::class.java)
//                    startActivity(intent)
//                    finish()
//
//                } else {// 회원가입이 안된다면
//                    Toast.makeText(
//                        this,
//                        "회원가입에 실패했습니다. 다시 한 번 확인해 주세요.",
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
//                        Toast.makeText(this, "아이디가 중복됩니다", Toast.LENGTH_SHORT).show()
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







