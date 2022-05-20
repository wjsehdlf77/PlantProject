package com.example.plantproject.Section

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.widget.ViewPager2
import com.example.plantproject.Adapter.FragmentViewPager
import com.example.plantproject.MainActivity
import com.example.plantproject.NaviFragment.HomeFragment
import com.example.plantproject.NaviFragment.Main2Fragment
import com.example.plantproject.NaviFragment.MyPageFragment
import com.example.plantproject.R
import com.example.plantproject.databinding.FragmentMain2Binding
import com.example.plantproject.databinding.FragmentMainSectionBinding


class MainSectionFragment : Fragment() {
    private var _binding: FragmentMainSectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navigationItemSelect()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_section_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun navigationItemSelect() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
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