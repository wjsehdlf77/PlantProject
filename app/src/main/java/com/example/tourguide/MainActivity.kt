package com.example.tourguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import com.example.tourguide.Fragment.HomeFragment
import com.example.tourguide.Fragment.MyPageFragment
import com.example.tourguide.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var  mypageFragment: MyPageFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(R.id.main_layout, homeFragment).commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.action_home -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_layout, homeFragment).commit()
            }

            R.id.action_mypage -> {
                mypageFragment = MyPageFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_layout, mypageFragment).commit()
            }
        }
        true
    }



}