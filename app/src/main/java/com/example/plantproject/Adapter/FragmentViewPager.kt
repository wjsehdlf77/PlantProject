package com.example.plantproject.Adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.plantproject.NaviFragment.HomeFragment
import com.example.plantproject.NaviFragment.Main2Fragment
import com.example.plantproject.NaviFragment.MyPageFragment
import com.example.plantproject.Section.MainSectionFragment
import com.example.plantproject.Section.ProfileSectionFragment

class FragmentViewPager(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

//    val fragmentList = listOf<Fragment>(HomeFragment(), Main2Fragment(), MyPageFragment())
//
//    override fun getItemCount(): Int { return fragmentList.size }
//
//    override fun createFragment(position: Int): Fragment { return fragmentList[position] }




    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MainSectionFragment()
            else -> ProfileSectionFragment()
        }
    }


}