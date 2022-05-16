package com.example.tourguide.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tourguide.MainActivity
import com.example.tourguide.R
import com.example.tourguide.databinding.FragmentMain2Binding


class Main2Fragment : Fragment() {
    private var _binding: FragmentMain2Binding? = null
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
        _binding = FragmentMain2Binding.inflate(inflater, container, false)
        return binding.root
    }



}