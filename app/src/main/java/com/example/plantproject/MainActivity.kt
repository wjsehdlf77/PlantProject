package com.example.plantproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.FragmentTransitionSupport
import androidx.viewpager2.widget.ViewPager2
import com.example.plantproject.Adapter.FragmentViewPager
import com.example.plantproject.DetailFragment.DetectPlantFragment
import com.example.plantproject.DetailFragment.MyPlantFragment
import com.example.plantproject.NaviFragment.HomeFragment
import com.example.plantproject.NaviFragment.Main2Fragment
import com.example.plantproject.NaviFragment.MyPageFragment
import com.example.plantproject.Section.MainSectionFragment
import com.example.plantproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.pager.adapter = FragmentViewPager(this)
//
//        binding.pager.registerOnPageChangeCallback(
//            object : ViewPager2.OnPageChangeCallback() {
//
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    binding.bottomNavigationView.menu.getItem(position).isChecked = true
//                }
//            }
//        )
//        navigationItemSelect()

        // 리스너 연결
//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.action_home -> {
//                    // ViewPager의 현재 item에 첫 번째 화면을 대입
//                    binding.pager.currentItem = 0
//                    return true
//                }
//                R.id.action_main2 -> {
//                    // ViewPager의 현재 item에 두 번째 화면을 대입
//                    binding.pager.currentItem = 1
//                    return true
//                }
//                R.id.action_mypage -> {
//                    // ViewPager의 현재 item에 세 번째 화면을 대입
//                    binding.pager.currentItem = 2
//                    return true
//                }
//                else -> {
//                    return false
//                }
//            }
//        }
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.action_home -> {
//                // ViewPager의 현재 item에 첫 번째 화면을 대입
//                binding.pager.currentItem = 0
//                return true
//            }
//            R.id.action_main2 -> {
//                // ViewPager의 현재 item에 두 번째 화면을 대입
//                binding.pager.currentItem = 1
//                return true
//            }
//            R.id.action_mypage -> {
//                // ViewPager의 현재 item에 세 번째 화면을 대입
//                binding.pager.currentItem = 2
//                return true
//            }
//            else -> {
//                return false
//            }
//        }
//    }



}


