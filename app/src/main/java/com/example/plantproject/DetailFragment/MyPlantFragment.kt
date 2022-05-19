package com.example.plantproject.DetailFragment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.plantproject.MainActivity
import com.example.plantproject.databinding.FragmentMyPlantBinding

import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class MyPlantFragment : Fragment() {
    lateinit var filePath: String
    private var _binding: FragmentMyPlantBinding? = null
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
        _binding = FragmentMyPlantBinding.inflate(inflater, container, false)
        return binding.root
    }




}