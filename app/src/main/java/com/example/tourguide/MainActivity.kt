package com.example.tourguide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.ui.setupWithNavController
import com.example.tourguide.Fragment.HomeFragment
import com.example.tourguide.Fragment.Main1Fragment
import com.example.tourguide.Fragment.Main2Fragment
import com.example.tourguide.Fragment.MyPageFragment
import com.example.tourguide.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationItemSelect()
//
//        homeFragment = HomeFragment.newInstance()
//        supportFragmentManager.beginTransaction()
//            .add(R.id.main_layout, homeFragment).commit()
//
//        binding.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

//    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
//        when(it.itemId) {
//            R.id.action_home -> {
//                homeFragment = HomeFragment.newInstance()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_layout, homeFragment).commit()
//            }
//
//            R.id.action_mypage -> {
//                mypageFragment = MyPageFragment.newInstance()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_layout, mypageFragment).commit()
//            }
//            R.id.action_main1 -> {
//                main1Fragment = Main1Fragment.newInstance()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_layout, main1Fragment).commit()
//            }
//            R.id.action_main2 -> {
//                main2Fragment = Main2Fragment.newInstance()
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.main_layout, main2Fragment).commit()
//            }
//        }
//        true
//    }
private fun replaceFragment(fragment: Fragment) {
    val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.main_layout, fragment)
    fragmentTransaction.commit()
}

    private fun navigationItemSelect() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.action_home -> replaceFragment(HomeFragment())
                    R.id.action_mypage -> replaceFragment(MyPageFragment())
                    R.id.action_main1 -> replaceFragment(Main1Fragment())
                    R.id.action_main2 -> replaceFragment(Main2Fragment())
                }
                true
            }
            selectedItemId = R.id.action_home
        }
    }


}