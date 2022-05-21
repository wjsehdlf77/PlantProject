package com.example.plantproject.DetailActivity

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.plantproject.databinding.ActivityDetectCheckBinding

class DetectCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetectCheckBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetectCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getParcelableExtra<Bitmap>("iamge")

        binding.imageView5.setImageBitmap(image)


    }

}