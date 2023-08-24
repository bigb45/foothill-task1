package com.example.logintask1.ui.home

import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.logintask1.R
import com.example.logintask1.data.ListItem
import com.example.logintask1.databinding.FragmentHomeBinding
import com.example.logintask1.ui.adapters.UsersListAdapter
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Random
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

val REQUIRED_PERMISSIONS = arrayOf(
    android.Manifest.permission.CAMERA,
)

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UsersListAdapter
    private var myList: ArrayList<ListItem>? = null

    //    setup the variables needed to capture an image
    private var imageCapture: ImageCapture? = null
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        with(binding) {
            lifecycleOwner = this@HomeFragment
//             binding.viewModel = viewModel
        }
        imageCapture = ImageCapture.Builder().build()
        cameraExecutor = Executors.newSingleThreadExecutor()

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }

        return binding.root
    }

    private val activityResultLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            var permissionGranted = true
            permissions.entries.forEach {
                if (it.key in REQUIRED_PERMISSIONS && !it.value) {
                    permissionGranted = false
                }

                if (!permissionGranted) {
                    Toast.makeText(
                        context,
                        "Permission not granted.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    startCamera()
                }
            }

        }

    private fun requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
//            to define the preview use case, we need 3 things:

//        a camera provider
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

//                a preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

//        and a camera selector
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
//            unbind previous use case
                cameraProvider.unbindAll()

//              you need to bind image capture to the camera selector as well as the preview

                val camera =
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)


                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner, cameraSelector, preview
                )

                imageCapture = ImageCapture.Builder()
                    .setTargetRotation(camera.cameraInfo.sensorRotationDegrees)
                    .build()

            } catch (e: Exception) {
                Log.e("couldnt", "launch preview")
            }
        }, ContextCompat.getMainExecutor(requireContext()))

    }

    private fun takePhoto() {
        val imageCapture = imageCapture ?: return

        val imageName = SimpleDateFormat("mm", Locale.US)
            .format(System.currentTimeMillis()) + "image name"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            requireContext().contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Log.e("camera error", "failed to capture image: $exception")
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Log.d("success", "captured image successfully")

                }
            }
        )

    }


    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
//        iterates over every permission in the array and checks if it has been granted

        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupRecyclerView()
        updateAdapter()
        setupButtonListener()
    }

    private fun setupAdapter() {
//                listener lambda function to pass to the adapter
        adapter = UsersListAdapter { item: ListItem, position: Int ->
            item.isExpanded = !item.isExpanded
            adapter.notifyItemChanged(position)
            Log.d("help", "${item.isExpanded}")
        }
    }

    private fun setupButtonListener() {
//        add list item button
        binding.buttonAddUser.setOnClickListener {
            val newList = ArrayList<ListItem>()
            newList.addAll(myList!!)
            val item = getRandomListItem()
            newList.add(item)
            myList = newList
            adapter.submitList(myList)
        }

//        capture image button
        binding.buttonCaptureImage.setOnClickListener {
            takePhoto()
        }
    }

    private fun getRandomListItem(): ListItem {
        val random = Random().nextInt(200)
        return ListItem(random, "$random item", details = "details of item $random")
    }

    private fun setupRecyclerView() {

        binding.recyclerViewUsers.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewUsers.adapter = adapter

//        stops the list item from flickering when click
        (binding.recyclerViewUsers.itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations =
            false
    }

    private fun updateAdapter() {
        myList = ArrayList()
        for (i in 0..2) {
            val item = ListItem(i, "Item $i", details = "details of item $i")
            myList?.add(item)
        }
        adapter.submitList(myList)
    }


}