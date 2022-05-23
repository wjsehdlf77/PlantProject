package com.example.plantproject.NaviFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction

import com.example.plantproject.DetailFragment.DetectPlantFragment
import com.example.plantproject.MainActivity
import com.example.plantproject.DetailFragment.MyPlantFragment
import com.example.plantproject.R
import com.example.plantproject.databinding.FragmentMyPageBinding

//마이페이지 프로필 수정

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
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
        _binding = FragmentMyPageBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnMyPlant.setOnClickListener {

            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom)
            fragmentTransaction.replace(R.id.main_section_layout, MyPlantFragment())
            fragmentTransaction.commit()





        }

        binding.btnWhat.setOnClickListener {

            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom)
            fragmentTransaction.replace(R.id.main_section_layout, DetectPlantFragment()).commitAllowingStateLoss()




        }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }


}