package com.example.plantproject.Section

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.plantproject.DetailActivity.DetectActivity
import com.example.plantproject.DetailActivity.DiaryActivity
import com.example.plantproject.DetailActivity.MyPlantActivity

import com.example.plantproject.MainActivity
import com.example.plantproject.NaviFragment.HomeFragment
import com.example.plantproject.NaviFragment.MyPageFragment
import com.example.plantproject.R
import com.example.plantproject.databinding.FragmentMainSectionBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_my_page.*


class MainSectionFragment : Fragment() {

    private var _binding: FragmentMainSectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    private var isFabOpen = false

    private var userid : String? = null

    private var homeFragment : HomeFragment? = null
    private var myPageFragment : MyPageFragment? = null



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



        binding.fabMain.setOnClickListener {
            toogleFab()
        }

        binding.fabMyPlant.setOnClickListener {
            toogleFab()
            val intent = Intent(mainActivity, MyPlantActivity::class.java)
            startActivity(intent)

        }

        binding.fabDiary.setOnClickListener {
            toogleFab()
            val intent = Intent(mainActivity, DiaryActivity::class.java)
            startActivity(intent)

        }

        binding.fabDetect.setOnClickListener {
            toogleFab()
            val intent = Intent(mainActivity, DetectActivity::class.java)
            startActivity(intent)

        }

    }




    private fun replaceFragment(fragment1: Fragment) {


        val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.fade_in,
            R.anim.fade_out,
        )
        fragmentTransaction.replace(R.id.main_section_layout, fragment1).commit()

    }

    private fun navigationItemSelect() {

        homeFragment = HomeFragment()
        val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.main_section_layout, homeFragment!!)

        fragmentTransaction.commit()


        binding.bottomNavigationView.setOnItemSelectedListener {

                when (it.itemId) {
                    R.id.action_mypage -> {
                        if (myPageFragment == null){
                            myPageFragment = MyPageFragment()
                            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
                            fragmentTransaction.setCustomAnimations(
                                R.anim.fade_in,
                                R.anim.fade_out,
                            )

                            fragmentTransaction.add(R.id.main_section_layout, myPageFragment!!).commit()


                        }
                        if(myPageFragment != null){
                            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
                            fragmentTransaction.setCustomAnimations(
                                R.anim.fade_in,
                                R.anim.fade_out,
                            )
                            fragmentTransaction.show(myPageFragment!!).commit()
                        }

                        if(homeFragment != null){
                            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
                            fragmentTransaction.hide(homeFragment!!).commit()
                        }

                        true
                    }
                    R.id.action_home -> {
                        if (homeFragment == null){
                            homeFragment = HomeFragment()
                            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
                            fragmentTransaction.setCustomAnimations(
                                R.anim.fade_in,
                                R.anim.fade_out,
                            )
                            fragmentTransaction.add(R.id.main_section_layout, homeFragment!!).commit()

                        }
                        if(homeFragment != null){
                            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
                            fragmentTransaction.setCustomAnimations(
                                R.anim.fade_in,
                                R.anim.fade_out,
                            )
                            fragmentTransaction.show(homeFragment!!).commit()
                        }
                        if(myPageFragment != null){
                            val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
                            fragmentTransaction.hide(myPageFragment!!).commit()
                        }

                        true

                    }

                    else ->{
                        true
                    }

                }

        }
    }


    private fun toogleFab() {

        if(isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabDetect, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabDetect, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabDiary, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabDiary, "translationX", 0f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabMyPlant, "translationY", 0f).apply { start()}

            ObjectAnimator.ofFloat(binding.fabMain, "translationY", 0f).apply { start()}
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 45f, 0f).apply { start() }
            binding.fabMain.setImageResource(R.drawable.ic_fabmain)

        } else {
            ObjectAnimator.ofFloat(binding.fabDetect, "translationY", -150f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabDetect, "translationX", 150f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabDiary, "translationY", -150f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabDiary, "translationX", -150f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabMyPlant, "translationY", -250f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabMain, "translationY", -100f).apply { start()}
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 45f, 0f).apply { start() }
            binding.fabMain.setImageResource(R.drawable.ic_close)

        }
        isFabOpen = !isFabOpen
    }





}