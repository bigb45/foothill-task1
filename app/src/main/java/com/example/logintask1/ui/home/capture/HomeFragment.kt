package com.example.logintask1.ui.home.capture

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logintask1.R
import com.example.logintask1.data.ListItem
import com.example.logintask1.databinding.FragmentHomeBinding
import com.example.logintask1.ui.home.capture.adapter.UsersListAdapter
import java.util.Random


val REQUIRED_PERMISSIONS = arrayOf(
    android.Manifest.permission.CAMERA,
)

class HomeFragment : Fragment(), TitleDialogFragment.InputDialogListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var myAdapter: UsersListAdapter
    private val viewModel: HomeViewModel by viewModels()
    private var myList: ArrayList<ListItem>? = null
    private var capturedImageUri: Uri? = null
    private var imageThumbnail: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        with(binding) {

//            this line single-handedly caused me 2 days of pain and suffering
            viewModel = this@HomeFragment.viewModel
            lifecycleOwner = this@HomeFragment
        }



        if (allPermissionsGranted()) {
//            startCamera()
        } else {
            requestPermissions()
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            openCameraToolbar.inflateMenu(R.menu.camera_toolbar_menu)
            openCameraToolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.open_camera -> {
                        dispatchCaptureIntent()
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }
        setupAdapter()
        setupRecyclerView()
        setupButtonListener()
        updateAdapter()
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    private fun dispatchCaptureIntent() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val values = ContentValues()
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        values.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        capturedImageUri = requireContext().contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri)
        if (cameraIntent.resolveActivity(requireActivity().packageManager) != null) {
            cameraAppLauncher.launch(cameraIntent)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private val cameraAppLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            imageThumbnail = requireContext().contentResolver.loadThumbnail(
                capturedImageUri!!,
                Size(250, 250),
                CancellationSignal()
            )

            val customDialog = TitleDialogFragment()
            customDialog.setInputDialogListener(this)
            customDialog.showDialog(childFragmentManager)

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



    @SuppressLint("NotifyDataSetChanged")
    private fun setupAdapter() {
        myAdapter = UsersListAdapter({ item: ListItem, _: Int ->
            item.isExpanded = !item.isExpanded
            Log.d("item", item.isExpanded.toString() + " " + item.title)
//            (cut my life into pieces) this is my last resort
            myAdapter.notifyDataSetChanged()

        }, { item ->
            val imageDialogFragment = FullImageDialog.newInstance(item.imageUri!!)
            imageDialogFragment.show(childFragmentManager, "Image_dialog")
        })
    }



    private fun setupButtonListener() {
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.fragment_posts)
        }
    }



    private fun createAndAddListItemWithImage(title: String, details: String) {

        val item = ListItem(
            imageUri = capturedImageUri,
            title = title,
            details = details,
            id = Random().nextInt(200),
            thumbnail = imageThumbnail!!
        )
        myList?.add(item)
        myAdapter.submitList(myList)
//        Log.d("item", myList.toString())
        myList?.let { myAdapter.notifyItemChanged(it.size) }
    }

    override fun onInputConfirmed(title: String, details: String) {
        createAndAddListItemWithImage(title, details)
    }

    private fun setupRecyclerView() {

        binding.recyclerViewUsers.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.adapter = myAdapter

////        stops the list item from flickering when click
//            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations =
//                false

        }
    }

    private fun updateAdapter() {
        myList = ArrayList()
        myAdapter.submitList(myList)
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
                    requireContext(), "Permission not granted.", Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

}