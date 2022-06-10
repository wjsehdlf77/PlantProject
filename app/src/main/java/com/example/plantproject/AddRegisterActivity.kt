package com.example.plantproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.plantproject.databinding.ActivityAddRegisterBinding
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage

class AddRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRegisterBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}



