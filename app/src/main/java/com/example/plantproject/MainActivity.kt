package com.example.plantproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.plantproject.NaviFragment.HomeFragment
import com.example.plantproject.NaviFragment.MyPageFragment
import com.example.plantproject.NaviFragment.Main2Fragment
import com.example.plantproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationItemSelect()


    }

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
                    R.id.action_main2 -> replaceFragment(Main2Fragment())
                }
                true
            }
            selectedItemId = R.id.action_home
        }
    }


}