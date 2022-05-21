package com.example.plantproject.DetailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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