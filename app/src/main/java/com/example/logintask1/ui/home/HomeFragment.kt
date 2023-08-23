package com.example.logintask1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.logintask1.R
import com.example.logintask1.data.ListItem
import com.example.logintask1.databinding.FragmentHomeBinding
import com.example.logintask1.ui.adapters.UsersListAdapter
import java.util.Random

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UsersListAdapter

    private var myList: ArrayList<ListItem>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        with(binding) {
            lifecycleOwner = this@HomeFragment
//             binding.viewModel = viewModel
        }
        return binding.root
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
        binding.buttonAddUser.setOnClickListener {
            val newList = ArrayList<ListItem>()
            newList.addAll(myList!!)
            val item = getRandomListItem()
            newList.add(item)
            myList = newList
            adapter.submitList(myList)
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
        for (i in 0..9) {
            val item = ListItem(i, "Item $i", details = "details of item $i")
            myList?.add(item)
        }
        adapter.submitList(myList)
    }


}