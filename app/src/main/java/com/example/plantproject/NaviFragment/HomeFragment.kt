package com.example.plantproject.NaviFragment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plantproject.MainActivity
import com.example.plantproject.R
import com.example.plantproject.databinding.FragmentHomeBinding


open class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val resources = this.resources
//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_leaf_main)
//        binding.picamera.setImageBitmap(bitmap) drawable -> bitmap

    }

}