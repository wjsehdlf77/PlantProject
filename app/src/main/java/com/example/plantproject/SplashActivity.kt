package com.example.plantproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Actionbar 제거
        supportActionBar?.hide()
        Glide.with(this).load(R.drawable.splash).into(imageView6);

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            Intent(this, LoginActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }, 3000)
    }
}
