package com.example.logintask1.ui.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.logintask1.data.ListItemClass

class myListAdapter: ListAdapter<ListItemClass, myListAdapter.MyViewHolder>(DiffCallback()) {
    class MyViewHolder(itemView: View) : ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class DiffCallback : DiffUtil.ItemCallback<ListItemClass>() {
    override fun areItemsTheSame(oldItem: ListItemClass, newItem: ListItemClass): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ListItemClass, newItem: ListItemClass): Boolean {
        return oldItem == newItem
    }
}