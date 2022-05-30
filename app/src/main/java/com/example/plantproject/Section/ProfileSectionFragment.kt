package com.example.plantproject.Section

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.plantproject.MainActivity
import com.example.plantproject.databinding.FragmentProfileSectionBinding




class ProfileSectionFragment : Fragment() {
    private var _binding: FragmentProfileSectionBinding? = null
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
        _binding = FragmentProfileSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sensorGraph.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        binding.sensorGraph.loadUrl("https://www.naver.com")

        binding.waterGraph.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
        }

        binding.waterGraph.loadUrl("https://www.google.com")

        binding.btnTemp.setOnClickListener {
            binding.sensorGraph.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }

            binding.sensorGraph.loadUrl("https://www.naver.com")

        }

        binding.btnHum.setOnClickListener {
            binding.sensorGraph.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }

            binding.sensorGraph.loadUrl("https://www.daum.net")
        }

        binding.btnLight.setOnClickListener {
            binding.sensorGraph.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
            }

            binding.sensorGraph.loadUrl("https://www.google.com")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}