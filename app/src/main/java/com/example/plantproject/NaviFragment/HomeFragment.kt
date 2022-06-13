package com.example.plantproject.NaviFragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.plantproject.LocalDB
import com.example.plantproject.Login.*
import com.example.plantproject.MainActivity
import com.example.plantproject.databinding.FragmentHomeBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import kotlin.concurrent.thread


open class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainActivity: MainActivity

    val ServerIP: String = "tcp://3.9.6.216:1883"
    val TOPIC: String = "android"
    val TOPIC_PB: String = "plant_pb"
    var msg = ""



    val DATABASE_VERSION = 1
    val DATABASE_NAME = "LocalDB.db"


    private lateinit var localDB: LocalDB

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


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thread(start = true) {
            loading(true)
            Thread.sleep(4000)
            loading(false)
            mainActivity.runOnUiThread {
                binding.mainLayout.visibility = View.VISIBLE
            }
        }


        localDB = LocalDB(mainActivity, DATABASE_NAME, null, DATABASE_VERSION)

        val id = localDB.returnID()

        binding.tempPro.max = 50
        binding.tempPro.min = -20
        binding.humPro.max = 100
        binding.humPro.min = 0
        binding.lightPro.max = 1000
        binding.lightPro.min = 0






        var retrofit = Retrofit.Builder()
            .baseUrl("http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000")

            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var getData: GetData = retrofit.create(GetData::class.java)

        getData.getSensor().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if (response.isSuccessful) {
                    val posts: List<Post>? = response.body()
                    val index = posts?.last()
                    val temp = index?.temp!!
                    val hum = index?.soil_hum!!
                    val light = index?.light!!

                    binding.tempDetail.text = "$temp°C"
                    binding.humDetail.text = "$hum%"
                    binding.lightDetail.text = "${light}lx"

                    binding.tempPro.progress = temp.toInt()
                    binding.humPro.progress = hum.toInt()
                    binding.lightPro.progress = light.toInt()
                } else {

                }
            }
        })


        val imageUpload = retrofit.create(ImageUpload::class.java)

        imageUpload.userPlantName(id).enqueue(object :Callback<GetLabel>{
            override fun onFailure(call: Call<GetLabel>, t: Throwable) {

            }

            override fun onResponse(call: Call<GetLabel>, response: Response<GetLabel>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    Log.d("아오", "${data}")

                    var label = data?.plantname!!
                    if (label == "옥살리스(사랑초)") {
                        label = "사랑초"
                    } else if (label == "골드크레스트 \"윌마\"") {
                        label = "윌마"
                    }
                    val name = data?.user
                    Log.d("아오", "${label},${name} ")

                    if (label != null) {
                        binding.myPlantName.setText(label)
//                        val health = "${label}은/는 건강해요!!!!"
//                        val hurt = "${label}은/는 너무아파요... 살려주세요"
//                        if (isHealth) {
//                            binding.myPlantHealth.text = health
//                        } else {
//                            binding.myPlantHealth.text = hurt
//                        }
                    }


                }
            }
        })



        getData.getHealth().enqueue(object :Callback<List<GetHealth>>{
            override fun onFailure(call: Call<List<GetHealth>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<GetHealth>>,
                response: Response<List<GetHealth>>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    val health = data?.last()
                    if (health?.health == "Your plants are healthy.") {
                        binding.myPlantHealth.setText("아글라오네마은/는 건강해요!!!!")
                    } else {
                        binding.myPlantHealth.setText("너무 아파요....")
                    }



                }
            }
        })




        val url = "http://ec2-18-170-251-149.eu-west-2.compute.amazonaws.com:8000/snapshot"
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val bitmap = withContext(Dispatchers.IO) {
                    MyPageFragment.imageLoader.loadImage(url)
                }
                Glide.with(mainActivity).load(bitmap).into(binding.picamera)
            } catch (e: Exception) {

                e.printStackTrace()
                Toast.makeText(requireContext(), "연결 오류", Toast.LENGTH_SHORT).show()
            }

        }




        binding.switchLed.isChecked = false

        var mqttClient: MqttClient? = null
        mqttClient = MqttClient(ServerIP, MqttClient.generateClientId(), null)
        mqttClient.connect()

//        binding.btnWater.setOnClickListener {
//            msg = ""
//            Log.d("MQTTService", "Send")
//            mqttClient.publish(TOPIC, MqttMessage("WATER".toByteArray()))
//        }

        binding.switchLed.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                msg = ""
                Log.d("MQTTService", "Send")
                mqttClient.publish(TOPIC, MqttMessage("LED_ON".toByteArray()))
            } else {
                msg = ""
                Log.d("MQTTService", "Send")
                mqttClient.publish(TOPIC, MqttMessage("LED_OFF".toByteArray()))
            }
        }

    }






    private fun loading(isShow: Boolean) {

        if (isShow) {
            binding.loadProgressbar.visibility = View.VISIBLE
        } else {
            binding.loadProgressbar.visibility = View.INVISIBLE
        }
    }

    override fun onStart() {
        super.onStart()

    }

    object imageLoader {

        suspend fun loadImage(imageUrl: String): Bitmap {


            val url = URL(imageUrl)
            val stream = url.openStream()

            return BitmapFactory.decodeStream(stream)

        }
    }

}







//        val resources = this.resources
//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_leaf_main)
//        binding.picamera.setImageBitmap(bitmap) drawable -> bitmap
