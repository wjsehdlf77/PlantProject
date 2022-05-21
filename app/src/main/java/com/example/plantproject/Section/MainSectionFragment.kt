package com.example.plantproject.Section

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
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


class MainSectionFragment : Fragment() {
    private var _binding: FragmentMainSectionBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    private var isFabOpen = false





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

            val intent = Intent(mainActivity, MyPlantActivity::class.java)
            startActivity(intent)

        }

        binding.fabDiary.setOnClickListener {
            val intent = Intent(mainActivity, DiaryActivity::class.java)
            startActivity(intent)
        }

        binding.fabDetect.setOnClickListener {
            val intent = Intent(mainActivity, DetectActivity::class.java)
            startActivity(intent)

        }



    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        )
        fragmentTransaction.replace(R.id.main_section_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun navigationItemSelect() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.action_home -> replaceFragment(HomeFragment())
                    R.id.action_mypage -> replaceFragment(MyPageFragment())

                }
                true
            }
            selectedItemId = R.id.action_home
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
            ObjectAnimator.ofFloat(binding.fabDetect, "translationY", -250f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabDetect, "translationX", 250f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabDiary, "translationY", -250f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabDiary, "translationX", -250f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabMyPlant, "translationY", -400f).apply { start() }

            ObjectAnimator.ofFloat(binding.fabMain, "translationY", -150f).apply { start()}
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 45f, 0f).apply { start() }
            binding.fabMain.setImageResource(R.drawable.ic_close)

        }
        isFabOpen = !isFabOpen
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    override fun onPause() {
        super.onPause()
        toogleFab()
    }



}