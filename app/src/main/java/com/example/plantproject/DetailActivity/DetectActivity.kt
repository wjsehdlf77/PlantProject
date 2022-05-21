package com.example.plantproject.DetailActivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.plantproject.databinding.ActivityDetectBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DetectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetectBinding
    private lateinit var filePath: String
    private lateinit var bitmapSource : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetectBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            try {
                val uri = it.data!!.data!!
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val source = ImageDecoder.createSource(contentResolver, uri)
                    ImageDecoder.decodeBitmap(source)?.let {
                        val bitmap = resizeBitmap(it, 900f, 0f)

                        bitmapSource = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                        binding.imageView3.setImageBitmap(bitmapSource)

                    }
                } else {
                    MediaStore.Images.Media.getBitmap(contentResolver, uri)?.let {
                        val bitmap = resizeBitmap(it, 900f, 0f)
                        bitmapSource = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                        binding.imageView3.setImageBitmap(bitmapSource)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        binding.btnGallary2.setOnClickListener {
            //gallery app........................
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        )
        {
            try {


                val file = File(filePath)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val source =
                        ImageDecoder.createSource(contentResolver, Uri.fromFile(file))
                    ImageDecoder.decodeBitmap(source)?.let {
                        val bitmap = resizeBitmap(it, 900f, 0f)
                        bitmapSource = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                        binding.imageView3.setImageBitmap(bitmapSource)

                    }
                } else {
                    MediaStore.Images.Media.getBitmap(
                        contentResolver,
                        Uri.fromFile(file)
                    )
                        ?.let {
                            val bitmap = resizeBitmap(it, 900f, 0f)
                            bitmapSource = bitmap.copy(Bitmap.Config.ARGB_8888, true)
                            binding.imageView3.setImageBitmap(bitmapSource)

                        }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.btnCamera2.setOnClickListener {

            val file = imageFile()
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.plantproject.fileprovider", file
            )

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            requestCameraFileLauncher.launch(intent)
        }

        binding.btnDetect.setOnClickListener {

            val intent = Intent(this, DetectCheckActivity::class.java)
            startActivity(intent)
        }

    }

    private fun imageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? =
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        var file = File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
        return file

    }

    private fun resizeBitmap(src: Bitmap, size: Float, angle: Float): Bitmap {
        val width = src.width
        val height = src.height
        var newWidth = 0f
        var newHeight = 0f
        if (width > height) {
            newWidth = size
            newHeight = height.toFloat() * (newWidth / width.toFloat())
        } else {
            newHeight = size
            newWidth = width.toFloat() * (newHeight / height.toFloat())
        }
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        val matrix = Matrix()
        matrix.postRotate(angle);
        matrix.postScale(scaleWidth, scaleHeight)
        val resizedBitmap = Bitmap.createBitmap(src, 0, 0, width, height, matrix, true)
        return resizedBitmap
    }
}