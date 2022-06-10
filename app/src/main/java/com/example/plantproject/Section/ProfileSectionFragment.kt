package com.example.plantproject.Section

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.plantproject.MainActivity
import com.example.plantproject.databinding.FragmentProfileSectionBinding
import java.net.URL


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
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
        }
        val url = "http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000/temp"
        binding.sensorGraph.loadUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000/temp")


//        binding.waterGraph.apply {
//            webViewClient = WebViewClient()
//            settings.javaScriptEnabled = true
//        }
//
//
//
//        binding.waterGraph.loadUrl("https://www.google.com")

        binding.btnTemp.setOnClickListener {
            binding.sensorGraph.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
            }

            binding.sensorGraph.loadUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000/temp")

        }



        binding.btnHum.setOnClickListener {
            binding.sensorGraph.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.useWideViewPort = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
            }

            binding.sensorGraph.loadUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000/light")
        }

        binding.btnLight.setOnClickListener {
            binding.sensorGraph.apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
            }

            binding.sensorGraph.loadUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000/humid")
        }

        binding.waterGraph.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
        }

        binding.waterGraph.loadUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000/water")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

    object imageLoader {

        suspend fun loadImage(imageUrl: String): Bitmap {


            val url = URL(imageUrl)
            val stream = url.openStream()

            return BitmapFactory.decodeStream(stream)

        }
    }

}