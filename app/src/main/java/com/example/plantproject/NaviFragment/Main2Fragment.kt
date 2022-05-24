package com.example.plantproject.NaviFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plantproject.MainActivity
import com.example.plantproject.databinding.FragmentMain2Binding


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