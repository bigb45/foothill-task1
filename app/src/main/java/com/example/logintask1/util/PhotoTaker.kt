package com.example.logintask1.util

import android.content.ContentValues
import android.content.Context
import android.media.Image
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.LifecycleOwner
import java.text.SimpleDateFormat
import java.util.Locale

class PhotoTaker(
    private val context: Context,
    private val lifecycleOwner: LifecycleOwner,
) {
    private lateinit var imageCapture: ImageCapture
    var imageLocation: Uri? = null


    fun takePhoto(
        onError: (ImageCaptureException) -> Unit,
        onSuccess: (Uri) -> Unit
    ) {
        val imageName =
            SimpleDateFormat("HH-mm-SS", Locale.US).format(System.currentTimeMillis()) + "image name"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            context.contentResolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues
        ).build()

        imageCapture.takePicture(outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    onError(exception)
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Log.d("image output", outputFileResults.savedUri.toString())
                    imageLocation = outputFileResults.savedUri
                    onSuccess(outputFileResults.savedUri!!)
                }
            })

    }

    fun setupCamera(preview: Preview){
        imageCapture =  ImageCapture.Builder().build()

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
//            to define the preview use case, we need 3 things:

//        a camera provider
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()




//        and a camera selector
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
//            unbind previous use case
                cameraProvider.unbindAll()

//              you need to bind image capture to the camera selector as well as the preview

                cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)


            } catch (e: Exception) {
                Log.e("Error", "Unable to launch preview: $e")
            }
        }, ContextCompat.getMainExecutor(context))

    }

}