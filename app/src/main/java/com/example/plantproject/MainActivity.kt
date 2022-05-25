package com.example.plantproject

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction


import com.example.plantproject.Adapter.FragmentViewPager
import com.example.plantproject.DetailActivity.DetectActivity
import com.example.plantproject.NaviFragment.HomeFragment

import com.example.plantproject.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var mBackWait:Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.pager.adapter = FragmentViewPager(this)



    }

    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
//            Snackbar.make(binding.pager,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Snackbar.LENGTH_SHORT).show()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        } else {
            finish() //액티비티 종료
        }
    }

}


