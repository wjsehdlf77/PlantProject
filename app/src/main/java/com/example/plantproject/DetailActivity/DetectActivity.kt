package com.example.plantproject.DetailActivity

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.plantproject.MainActivity
import com.example.plantproject.databinding.ActivityDetectBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class DetectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetectBinding

    private var imageCapture: ImageCapture? = null

    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLater.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }



        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        binding.imageCapture.setOnClickListener { takePhoto() }

        cameraExecutor = Executors.newSingleThreadExecutor()


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
                        val intent = Intent(baseContext, DetectCheckActivity::class.java)

                        startActivity(intent)
//                        bitmapSource = bitmap.copy(Bitmap.Config.ARGB_8888, true)
//                        binding.imageView3.setImageBitmap(bitmapSource)

                    }
                } else {
                    MediaStore.Images.Media.getBitmap(contentResolver, uri)?.let {
                        val bitmap = resizeBitmap(it, 900f, 0f)

                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        binding.gallaryBtn.setOnClickListener {
            //gallery app........................
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        // Create time stamped name and MediaStore entry.
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
//                    val msg = "Photo capture succeeded: ${output.savedUri}"
//                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()


                }
            }
        )
    }


    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }



    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }


//
//        val requestCameraFileLauncher = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        )
//        {
//            try {
//
//
//                val file = File(filePath)
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                    val source =
//                        ImageDecoder.createSource(contentResolver, Uri.fromFile(file))
//                    ImageDecoder.decodeBitmap(source)?.let {
//                        val bitmap = resizeBitmap(it, 900f, 0f)
//                        bitmapSource = bitmap.copy(Bitmap.Config.ARGB_8888, true)
//                        binding.imageView3.setImageBitmap(bitmapSource)
//
//                    }
//                } else {
//                    MediaStore.Images.Media.getBitmap(
//                        contentResolver,
//                        Uri.fromFile(file)
//                    )
//                        ?.let {
//                            val bitmap = resizeBitmap(it, 900f, 0f)
//                            bitmapSource = bitmap.copy(Bitmap.Config.ARGB_8888, true)
//                            binding.imageView3.setImageBitmap(bitmapSource)
//
//                        }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//        binding.btnCamera2.setOnClickListener {
//
//            val file = imageFile()
//            filePath = file.absolutePath
//            val photoURI: Uri = FileProvider.getUriForFile(
//                this,
//                "com.example.plantproject.fileprovider", file
//            )
//
//            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//            requestCameraFileLauncher.launch(intent)
//        }
//
//        binding.btnDetect.setOnClickListener {
//
//            val intent = Intent(this, DetectCheckActivity::class.java)
//            startActivity(intent)
//        }


    //    private fun imageFile(): File {
//        val timeStamp: String =
//            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        val storageDir: File? =
//            getExternalFilesDir(Environment.DIRECTORY_PICTURES)
//        var file = File.createTempFile(
//            "JPEG_${timeStamp}_",
//            ".jpg",
//            storageDir
//        )
//        return file
//
//    }
//
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


//    private fun bindPreview(cameraProvider : ProcessCameraProvider) {
//        var preview : Preview = Preview.Builder()
//            .build()
//
//        var cameraSelector : CameraSelector = CameraSelector.Builder()
//            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
//            .build()
//
//        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
//
//        var camera = cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
//    }




