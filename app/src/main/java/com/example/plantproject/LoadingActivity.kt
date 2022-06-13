package com.example.plantproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.plantproject.databinding.ActivityLoadingBinding
import com.example.plantproject.databinding.ActivityLoginBinding
import kotlin.concurrent.thread

class LoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        thread(start = true) {
            Thread.sleep(8000)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

//    private fun loading(isShow: Boolean) {
//
//        if (isShow) {
//            binding.loadProgressbar.visibility = View.VISIBLE
//        } else {
//            binding.loadProgressbar.visibility = View.INVISIBLE
//        }
//    }
}