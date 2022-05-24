package com.example.plantproject.DetailActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plantproject.R
import com.example.plantproject.databinding.ActivityDiaryBinding


class DiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}