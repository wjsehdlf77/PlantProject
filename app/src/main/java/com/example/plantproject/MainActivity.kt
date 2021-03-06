package com.example.plantproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.viewpager2.widget.ViewPager2


import com.example.plantproject.Adapter.FragmentViewPager
import com.example.plantproject.NaviFragment.HomeFragment
import com.example.plantproject.NaviFragment.MyPageFragment
import com.example.plantproject.Section.MainSectionFragment
import com.example.plantproject.databinding.ActivityMainBinding

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_detect.*


class MainActivity : AppCompatActivity(),  BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    var mBackWait:Long = 0

    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"

    private lateinit var localDB: LocalDB




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        localDB= LocalDB(this, DATABASE_NAME,null, DATABASE_VERSION)


         val page = FragmentViewPager(this)
         binding.pager.adapter = page

        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.mainTopNavi.menu.getItem(position).isChecked = true
                }
            }
        )

        // 리스너 연결
        binding.mainTopNavi.setOnNavigationItemSelectedListener(this)

//        setDataAtFragment(HomeFragment(), userId)

    }
//    fun setDataAtFragment(fragment: Fragment, userid: String?){
//
//        bundle.putString("id",userid)
//        Log.d("아오", "$userid")
//        fragment.arguments =bundle
//    }



    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
//            Snackbar.make(binding.pager,"뒤로가기 버튼을 한번 더 누르면 종료됩니다.",Snackbar.LENGTH_SHORT).show()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        } else {
            localDB.deleteData()
            finish() //액티비티 종료
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_viewpager_home -> {
                // ViewPager의 현재 item에 첫 번째 화면을 대입
                binding.pager.currentItem = 0
                return true
            }
            R.id.action_viewpager_cummunity -> {
                // ViewPager의 현재 item에 두 번째 화면을 대입
                binding.pager.currentItem = 1
                return true
            }else -> {
                return false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        localDB.deleteData()
    }

}


