package com.example.plantproject.DetailFragment

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.plantproject.MainActivity
import com.example.plantproject.databinding.FragmentDetectCheckBinding


class DetectCheckFragment : Fragment() {


    private lateinit var mainActivity : MainActivity

    private var _binding: FragmentDetectCheckBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setFragmentResultListener("requestKey") { key, bundle ->
//            // We use a String here, but any type that can be put in a Bundle is supported
//            val result = bundle.getParcelable<Bitmap>("bundleKey")
//
//            binding.imageView5.setImageBitmap(result)
//        }
    }


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentDetectCheckBinding.inflate(inflater, container, false)
            return binding.root
        }

}