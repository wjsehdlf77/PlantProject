package com.example.plantproject.DetailActivity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.plantproject.R
import com.example.plantproject.databinding.ActivityMyPlantBinding

class MyPlantActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPlantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMyPlantBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }




}