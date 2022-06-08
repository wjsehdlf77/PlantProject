package com.example.plantproject.NaviFragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.plantproject.MainActivity
import com.example.plantproject.R
import com.example.plantproject.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_my_page.*


open class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    val temp = "30"
    val hum = "30"
    val light = "5000"
    val name = "율마"
    val isHealth:Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tempPro.max = 50
        binding.tempPro.min = -20
        binding.humPro.max = 100
        binding.humPro.min = 0
        binding.lightPro.max = 30000
        binding.lightPro.min = 0

        binding.tempDetail.text = "$temp°C"
        binding.humDetail.text = "$hum%"
        binding.lightDetail.text = "${light}lx"

        binding.tempPro.progress = temp.toInt()
        binding.humPro.progress = hum.toInt()
        binding.lightPro.progress = light.toInt()
        binding.myPlantName.text = name

        val health = "${name}은/는 건강해요!!!!"
        val hurt = "${name}은/는 너무아파요... 살려주세요"
        if (isHealth) {
            binding.myPlantHealth.text = health
        } else {
            binding.myPlantHealth.text = hurt
        }







//        val resources = this.resources
//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_leaf_main)
//        binding.picamera.setImageBitmap(bitmap) drawable -> bitmap
