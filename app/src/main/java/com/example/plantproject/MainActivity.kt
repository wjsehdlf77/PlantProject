package com.example.plantproject

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction


import com.example.plantproject.Adapter.FragmentViewPager
import com.example.plantproject.NaviFragment.HomeFragment

import com.example.plantproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.pager.adapter = FragmentViewPager(this)


    }

}


