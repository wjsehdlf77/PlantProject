package com.example.plantproject.DetailActivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.plantproject.LocalDB
import com.example.plantproject.Login.GetData
import com.example.plantproject.Login.GetUser
import com.example.plantproject.Login.Post
import com.example.plantproject.MainActivity
import com.example.plantproject.R
import com.example.plantproject.databinding.ActivityAddRegisterBinding
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class AddRegisterActivity : AppCompatActivity() {

    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"

    private lateinit var localDB: LocalDB

    private lateinit var binding: ActivityAddRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        thread(start = true) {
            loading(true)
            Thread.sleep(3000)
            loading(false)
            runOnUiThread {
                binding.addRegisterInLayout.visibility = View.VISIBLE
            }
        }



        localDB = LocalDB(this, DATABASE_NAME, null, DATABASE_VERSION)
        val localId = localDB.returnID()


        var retrofit = Retrofit.Builder()
//            .baseUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000")
            .baseUrl("http://192.168.0.4:8000")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val getData: GetData = retrofit.create(GetData::class.java)

        getData.getUserData(localId).enqueue(object : Callback<GetUser> {
            override fun onFailure(call: Call<GetUser>, t: Throwable) {

            }

            override fun onResponse(call: Call<GetUser>, response: Response<GetUser>) {
                if (response.isSuccessful) {
                    val userdata = response.body()


                    binding.changeIdDetail.setText(userdata?.userid)
                    binding.changeNameDetail.setText(userdata?.username)
                    binding.changeBirthDetail.setText(userdata?.userbirth)
                }
            }
        })

        binding.btnUpdate.setOnClickListener {
            val id = binding.changeIdDetail.text.toString()
            val pw = binding.changePasswordDetail.text.toString()
            val pwck = binding.PasswordCheckDetail.text.toString()
            val name = binding.changeNameDetail.text.toString()
            val birth = binding.changeBirthDetail.text.toString()
            if (id.isEmpty() || pw.isEmpty() || pwck.isEmpty() || name.isEmpty() || birth.isEmpty()){
                Toast.makeText(baseContext, "값을 다 채워주세요", Toast.LENGTH_SHORT).show()
            } else {
                if  (pw == pwck) {

                    thread(start = true) {
                        runOnUiThread {
                            binding.addRegisterInLayout.visibility = View.INVISIBLE
                            loading(true) }
                    }
                    updateUserData(id, pw, name, birth)
                } else{
                    Toast.makeText(baseContext, "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }



        }


    }

    private fun loading(isShow: Boolean) {

        if (isShow) {
            binding.loadinglayout.visibility = View.VISIBLE
            binding.loadProgressbar.visibility = View.VISIBLE
        } else {
            binding.loadProgressbar.visibility = View.INVISIBLE
            binding.loadinglayout.visibility = View.INVISIBLE
        }
    }
    private fun updateUserData(id:String, pw:String, name:String, birth:String) {

        var retrofit = Retrofit.Builder()
//            .baseUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000")
            .baseUrl("http://192.168.0.4:8000")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val getData: GetData = retrofit.create(GetData::class.java)

        getData.getPutField(id,id, pw, name, birth).enqueue(object : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext, "회원 정보 수정", Toast.LENGTH_SHORT).show()


                    overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_top)
                    finish()
                }

            }
        })

    }

}