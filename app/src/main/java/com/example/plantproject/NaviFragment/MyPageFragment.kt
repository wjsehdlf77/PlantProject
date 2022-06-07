package com.example.plantproject.NaviFragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.plantproject.LocalDB
import com.example.plantproject.Login.ImageUpload
import com.example.plantproject.Login.LoginService

import com.example.plantproject.MainActivity

import com.example.plantproject.databinding.FragmentMyPageBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//마이페이지 프로필 수정

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"

    private lateinit var localDB: LocalDB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(inflater, container,false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        localDB= LocalDB(mainActivity, DATABASE_NAME,null, DATABASE_VERSION)
        val id = localDB.returnID()
        binding.userid.text = id


        var retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.4:8000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var imageUpload = retrofit.create(ImageUpload::class.java)

        imageUpload.userProfileImage(id)?.enqueue(object : Callback<ResponseBody?> {
            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                Log.d("아우", response.message())
            }
        })
    }








}