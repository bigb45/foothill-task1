package com.example.logintask1.ui.home

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

import androidx.camera.core.Preview
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.logintask1.R
import com.example.logintask1.data.ListItem
import com.example.logintask1.databinding.FragmentHomeBinding
import com.example.logintask1.ui.adapters.UsersListAdapter
import com.example.logintask1.util.PhotoTaker
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
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var photoTaker: PhotoTaker
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        with(binding) {
            lifecycleOwner = this@HomeFragment
//             binding.viewModel = viewModel
        }
        cameraExecutor = Executors.newSingleThreadExecutor()

        photoTaker = PhotoTaker(requireContext(), viewLifecycleOwner)

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions()
        }
        return binding.root
    }

    private val activityResultLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        var permissionGranted = true
        permissions.entries.forEach {
            if (it.key in REQUIRED_PERMISSIONS && !it.value) {
                permissionGranted = false
            }

            if (!permissionGranted) {
                Toast.makeText(
                    context, "Permission not granted.", Toast.LENGTH_SHORT
                ).show()
            } else {
                startCamera()
            }
        }

    }

    private fun requestPermissions() {
        activityResultLauncher.launch(REQUIRED_PERMISSIONS)
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
        setupButtonListener()
        updateAdapter()
    }

    private fun setupAdapter() {
//                listener lambda function to pass to the adapter
        adapter = UsersListAdapter { item: ListItem, position: Int ->
            item.isExpanded = !item.isExpanded
            adapter.notifyItemChanged(position)
        }
    }

    private fun startCamera() {
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
        }
        photoTaker.setupCamera(preview)

    }

    private fun setupButtonListener() {

        binding.buttonCaptureImage.setOnClickListener {
            takePhoto()
//            Log.d("image uri", imageUri.toString())
//            createAndAddListItemWithImage(imageUri)
        }
    }

    private fun takePhoto() {
        photoTaker.takePhoto({ e ->
            Log.e("Image capture error", "Error: $e")
        }) { imageUri ->
                createAndAddListItemWithImage(imageUri)
        }
    }

    private fun getListItem(uri: Uri?): ListItem {
        val id = Random().nextInt(200)

        return ListItem(
            id,
            "greatest image of all time",
            uri,
            "this is the greatest picture of all time"
        )
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
        adapter.submitList(myList)

    }

    private fun createAndAddListItemWithImage(imageUri: Uri?) {
        val newList = ArrayList<ListItem>()
        newList.addAll(myList!!)
        val item = getListItem(imageUri)
        newList.add(item)
        myList = newList
        adapter.submitList(myList)

        Log.d("Success", "Saved image successfully")
        Toast.makeText(
            requireContext(), "image saved successfully", Toast.LENGTH_SHORT
        ).show()
    }
}