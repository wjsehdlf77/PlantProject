package com.example.plantproject.DetailFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.plantproject.MainActivity
import com.example.plantproject.R
import com.example.plantproject.databinding.FragmentDetailDiaryBinding
import com.example.plantproject.databinding.FragmentDetectCheckBinding


class DetailDiaryFragment : Fragment() {
    private lateinit var mainActivity : MainActivity

    private var _binding: FragmentDetailDiaryBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailDiaryBinding.inflate(inflater, container, false)
        return binding.root
    }


}