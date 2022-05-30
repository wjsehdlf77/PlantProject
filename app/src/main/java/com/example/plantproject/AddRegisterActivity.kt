package com.example.plantproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plantproject.databinding.ActivityAddRegisterBinding

class AddRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}